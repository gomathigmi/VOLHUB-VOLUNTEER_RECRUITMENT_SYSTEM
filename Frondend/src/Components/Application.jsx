import {
  Avatar,
  AvatarGroup,
  Box,
  Button,
  Grid,
  Image,
  Input,
  Text,
  Textarea,
  useToast,
  IconButton,
} from "@chakra-ui/react";
import { GiBurningDot } from "react-icons/gi";
import { CiCirclePlus } from "react-icons/ci";
import { IoSend } from "react-icons/io5";
import { FaCloudDownloadAlt } from "react-icons/fa";
import { MdClose } from "react-icons/md";
import { UsedbContext } from "../Services/UseContext";
import { useEffect, useState } from "react";
import { CreateApplication, CreateNotification, GetApplication } from "../Services/api";

function Application() {
  const { isVolunteer, upload_brouche_firbase, dbUser } = UsedbContext();
  const [isApplication, setIsApplication] = useState(false);
  const [isApply, setIsApply] = useState(false);
  const [brouche, setBrouche] = useState(null);
  const [cover, setCover] = useState(null);
  const [eventname, setEventname] = useState("");
  const [eventtime, setEventtime] = useState("");
  const [eventdes, setEventdes] = useState("");
  const [applications, setApplications] = useState([]);
  const [applyname, setApplyname] = useState('');
  const [applydes, setApplydes] = useState('');
  const [searchTerm, setSearchTerm] = useState('');
  const toast = useToast();

  useEffect(() => {
    const getAllApplications = async () => {
      const res = await GetApplication();
      setApplications(res);
    };
    getAllApplications();
  }, []);

  const handleFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
      setBrouche(file);
      console.log(file);
    }
  };

  const handleFileChange2 = (event) => {
    const file = event.target.files[0];
    if (file) {
      setCover(file);
      console.log(file);
    }
  };

  const handleSend = async () => {
    const name = brouche.name + Date.now();
    const brou = await upload_brouche_firbase(brouche, name, "brouche");
    const cov = await upload_brouche_firbase(cover, name, "cover");

    await CreateApplication({
      url: cov,
      description: eventdes,
      organizerid: dbUser.id,
      eventname: eventname,
      brouche: brou,
      time: eventtime,
    });

    setIsApplication(false);
  };

  const handleConfirm = async () => {
    toast({
      title: "Congratulations!",
      description: "Your application has been sent successfully!",
      status: "success",
      position: "top-right",
      duration: 5000,
      isClosable: true,
    });
    onClose();
  };

  const onClose = () => {
    setIsApply(false);
  };

  const [applyitem, setapplyitem] = useState(null);
  const onApplyClick = (item) => {
    setapplyitem(item);
    setIsApply(!isApply);
  };

  const ConfirmClick = async () => {
    console.log(applyitem);
    await CreateNotification({
      volunteerid: dbUser?.id,
      organizerid: applyitem?.organizerid,
      notifytype: 'Pending',
      notifycontent: "Organizer Still not viewed.",
      applicationid: applyitem?.id
    });
    await handleConfirm(); // Ensure confirmation is handled
  };

  const handleEventNameChange = (e) => setEventname(e.target.value);
  const handleEventTimeChange = (e) => setEventtime(e.target.value);
  const handleEventDesChange = (e) => setEventdes(e.target.value);

  const filteredApplications = applications.filter(app => 
    app.eventname.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <>
      <Box className="w-full h-screen flex flex-col">
        {/* Top Section */}
        <Box className="w-full flex items-center justify-between p-7 bg-customBg" borderColor={'gray.400'}>
          <Text className="text-3xl font-bold text-white">Start Your Volunteering Now!</Text>
          {isVolunteer && (
            <Input
              placeholder="Search events..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              width="350px"
            />
          )}
        </Box>

        {/* Content Section */}
        <Box className="w-full h-full p-4 overflow-auto bg-customBg">
          {/* Event Cards */}
          <Grid templateColumns="repeat(4, 1fr)" gap={6} className="w-full">
            {filteredApplications.map((item, index) => (
              <Box
                className="bg-white w-full rounded-xl mt-2 shadow-lg transform transition-transform duration-300 hover:scale-105"
                key={index}
              >
                <Image
                  src={item?.url}
                  w={"full"}
                  h={"190px"}
                  objectFit={"cover"}
                  borderTopRadius={"10px"}
                />
                <Box className="p-4">
                  <Text className="text-xl font-medium mt-1" color={'black'}>{item?.eventname}</Text>
                  <Text className="text-lg font-medium mt-1" color={'black'}>{item?.time}</Text>
                  <Box className="flex items-center gap-2 mt-2">
                    <Button
                      
                      className="bg-blue-800 hover:opacity-50"
                      color={'black'}
                    >
                      32 members
                    </Button>
                    <AvatarGroup size="sm" className="ml-auto">
                      <Avatar name="Ryan Florence" src="https://bit.ly/ryan-florence" />
                      <Avatar name="Segun Adebayo" src="https://bit.ly/sage-adebayo" />
                      <Avatar name="Kent Dodds" src="https://bit.ly/kent-c-dodds" />
                    </AvatarGroup>
                  </Box>
                  {isVolunteer && <Button className="w-full mt-2"  bg="#2980B9" color="white" _hover={{ bg:"customDarkBlue" }} onClick={() => onApplyClick(item)}>Apply</Button>}
                  <Box className="flex justify-center items-center mt-1">
                    <GiBurningDot size={"18px"} className="text-center text-green-500" />
                    <Text className="text-center text-green-500">Upcoming</Text>
                    <Box cursor={'pointer'} className="ml-auto">
                      <a href={item.brouche} download target="_blank" rel="noopener noreferrer">
                        <FaCloudDownloadAlt className="text-custompink" size={'18px'} />
                      </a>
                    </Box>
                  </Box>
                </Box>
              </Box>
            ))}
          </Grid>
        </Box>
      </Box>

      {/* Create Event Button */}
      {!isVolunteer && (
        <Box className="fixed bottom-16 right-10" cursor={"pointer"} onClick={() => setIsApplication(!isApplication)}>
          <CiCirclePlus size={"60px"} className="text-white" />
        </Box>
      )}

      {/* Create Event Form */}
      {isApplication && (
        <Box className="bg-white fixed top-5 ml-72 w-4/12 h-[660px] mt-6 rounded-[50px] shadow-lg p-6 overflow-y-auto z-50">
          <Box className="w-full h-full">
            <IconButton
              aria-label="Close"
              icon={<MdClose />}
              onClick={() => setIsApplication(false)}
              position="absolute"
              top="10px"
              right="10px"
              colorScheme="gray"
            />
            <Text className="text-3xl font-bold text-center text-black">Create an Event</Text>
            <Box borderBottom={"1px solid black"} className="ml-4 mb-4" />
            <Box>
              <Input
                autoComplete="on"
                type="text"
                placeholder="Event Name"
                _focus={{ boxShadow: "none", outline: "none", border: "none" }}
                border={"none"}
                className="mt-2"
                onChange={handleEventNameChange}
              />
              <Box borderBottom={"1px solid black"} className="ml-4 mb-4" />
              <Input
                autoComplete="on"
                type="text"
                placeholder="Time"
                _focus={{ boxShadow: "none", outline: "none", border: "none" }}
                border={"none"}
                className="mt-2"
                onChange={handleEventTimeChange}
              />
              <Box borderBottom={"1px solid black"} className="ml-4 mb-4" />
              <Textarea
                height={"60vh"}
                className="mt-4"
                onChange={handleEventDesChange}
              />
            </Box>
            <Input
              type="file"
              display={"none"}
              id="brouche"
              onChange={handleFileChange}
            />
            <Input
              type="file"
              display={"none"}
              id="frontcover"
              onChange={handleFileChange2}
              accept="image/*"
            />
            <Box className="flex items-center mt-4">
              <Button className="mt-2">
                <label htmlFor="brouche">Upload Brouche</label>
              </Button>
              <Button className="mt-2 ml-2">
                <label htmlFor="frontcover">Upload Front Cover</label>
              </Button>
              <IoSend className="ml-auto mr-8 text-custompink" onClick={handleSend} />
            </Box>
          </Box>
        </Box>
      )}

      {/* Apply Confirmation Modal */}
      {isApply && (
  <Box className="bg-white fixed top-1/4 left-1/4 w-5/12 h-auto mt-6 rounded-[20px] shadow-lg p-4 z-50">
    <Box className="fixed inset-0 bg-black opacity-50" />
    <Box className="relative z-10 bg-white p-4 rounded-lg shadow-lg">
      <IconButton
        aria-label="Close"
        icon={<MdClose color="black" />} 
        onClick={() => setIsApply(false)}
        position="absolute"
        top="10px"
        right="10px"
         // Set the color to gray
      />
      <Text className="text-left text-xl font-bold mb-2 text-orange-600">{applyitem.eventname}</Text>
      <Text mb-4 className="text-lg text-orange-600">{applyitem.description}</Text>
      <Text className="text-center mt-8 text-black">Do you wish to confirm your application?</Text>
      <Box className="flex justify-center mt-4">
        <Button colorScheme="green" onClick={ConfirmClick}>Confirm</Button>
      </Box>
    </Box>
  </Box>
)}
    </>
  );
}

export default Application;