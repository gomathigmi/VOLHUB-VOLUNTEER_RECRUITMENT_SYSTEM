import { useCallback, useEffect, useRef, useState } from "react";
import { UsedbContext } from "../Services/UseContext";
import { GetMessage, SendMessage } from "../Services/api";
import { socket } from "../Services/Socket";
import { Avatar, Box, Button, Image, Input, Menu, MenuButton, MenuItem, MenuList, Spinner, Text } from "@chakra-ui/react";
import { FaCamera, FaCloudDownloadAlt, FaFilePdf, FaFileWord, FaLongArrowAltLeft, FaPhoneAlt } from "react-icons/fa";
import { IoIosMic, IoIosVideocam, IoMdCheckmark, IoMdPhotos, IoMdSearch, IoMdVideocam } from "react-icons/io";
import { BsFillFileEarmarkPptFill, BsThreeDotsVertical } from "react-icons/bs";
import { IoCheckmarkDone, IoDocumentText, IoDocumentTextSharp } from "react-icons/io5";
import { formatTime } from "../Services/Time";
import { SiGooglesheets } from "react-icons/si";
import { MdOutlineAttachFile } from "react-icons/md";
import EmojiPicker from "emoji-picker-react";
import { FaRegFaceGrin } from "react-icons/fa6";


function ChatmsgDrawer({reciverid,conversationid}) {

    const [showEmojiPicker, setShowEmojiPicker] = useState(false);
  
    const [msg, setmsg] = useState('');
    const [file, setfile] = useState(null);
  
    const {dbUser}=UsedbContext();
  
    const [messages,setmessages]=useState([]);
    const [refresh,setrefresh]=useState(false);
    const [srefresh,setsrefresh]=useState(false);
    const messagesEndRef = useRef(null);
    useEffect(() => {
      messagesEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [messages,refresh,srefresh]);
  
  
    useEffect(()=>
    {
      const getmsg=async()=>
      {
        const msg=await GetMessage(conversationid);
        
        setmessages(msg);
      }
      getmsg();
    },[conversationid,srefresh])
  
    // -------------socket
    const handemsgrecive=useCallback((data)=>{
      console.log(data);
      setsrefresh(!srefresh);
    },[srefresh])
    useEffect(() => {
      // Register the event listener
      socket.on("msg-receive", handemsgrecive);
  
      // Cleanup function to remove the event listener
      return () => {
          socket.off("msg-receive", handemsgrecive);
      };
  }, [handemsgrecive]);
  
    const sendMsg = async () => {
  
      const data={
        "message": msg,
        "sender": String(dbUser.id),
        "receiver": reciverid,
        "time": Date.now(),
        "conversationid": conversationid,
        "status": "sent",
        "type":"text"
      }
      await SendMessage(data);
      messages.push(data);
      setrefresh(!refresh);
      setmsg('');  
      socket.emit('msg-send',reciverid);
    }
  
    const handleTextInput = (event) => {
      setmsg(event.target.value);
    };
  
    const handleKeyDown = (event) => {
      if (event.key === 'Enter' || event.keyCode === 13) {
        event.preventDefault(); // Prevents the default action (like form submission)
        sendMsg();
      }
    };
   
    const handleEmojiClick = (emoji) => {
      setmsg((prevMsg) => prevMsg + emoji.emoji);
      setShowEmojiPicker(false);
    };
  const Documentinput=(e)=>
  {
    setfile(e.target.files[0]);      
  }
  
  
   
    return (
      <>
  
      <Box className="flex flex-col">
          
  
          <Box className="flex items-center p-2 justify-between bg-customwhite rounded-t-[50px]">
            <Box className="flex items-center  gap-3 ml-3">
              <FaLongArrowAltLeft size={"20px"} color="gray" />
              <Avatar src=""/>
              <Box>
                <Text className="text-black">Jon</Text>
                <Text className="text-black">Online</Text>
  
              </Box>
             
            </Box>
             
            <div className="flex ml-auto items-center gap-8 mr-3">
                    <FaPhoneAlt size={"20px"} color="gray"/>
                    <IoMdVideocam size={"20px"} color="gray"/>
                    <IoMdSearch size={"30px"} color="gray" />
                    <BsThreeDotsVertical size={"28px"} color="gray"/>
              </div>
  
          </Box>
  {/* ------------------------------message -------------body------ */}
          <Box className=" h-[65vh] overflow-auto w-full no-scrollbar">
          {messages?.map((item,index)=>
          (
            <Box 
            key={index} 
            p={2}>
            {String(dbUser.id)===item.sender?
             <Box display="flex" flexDirection={"row-reverse"}>
                                          <Box 
                    className="p-2 flex items-center  bg-green-200 text-customBg" 
                    borderRadius={"10px"}
                    maxW={"600px"} 
  
                    >
                      <Box className="flex items-center  gap-2">
  
                        
                            {item.type!="text"? 
                              
                              <Box>
                                {/* -----------Audio----------------------- */}
                                {item.type=="Audio"&&
                                
                                  <audio src={item.url} controls />
                                  
                                
  
                                }
                                {/* -----------Video----------------------- */}
                                {
                    item.type.startsWith("video/")&&
                    
                      <video src={item.url} width={'250px'} height={'250px'} controls/>
                      
                                 }
  
                                {/* --------------------imges------ */}
                                {
                                  item.type.startsWith("image/")&&
                                  
                                    <Image src={item.url} width={'full'}/>
                                    
                                  }
  {/* -----------------------------files---------------- */}
                <Box className="flex items-center">
                {(item.type === "application/vnd.ms-powerpoint" || item.type === "application/vnd.openxmlformats-officedocument.presentationml.presentation") && <BsFillFileEarmarkPptFill />}
              {/* pdf logo */}
              {item.type === "application/pdf" && <FaFilePdf size={"40px"} color="red" />}
              {/* text logo */}
              {item.type === "text/plain" && <IoDocumentTextSharp size={"40px"} color="red" />}
              {/* word logo */}
              {(item.type === "application/msword" || item.type === "application/vnd.openxmlformats-officedocument.wordprocessingml.document") && <FaFileWord />}
              {/* sheets */}
              {(item.type === "application/vnd.ms-excel" || item.type === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") && <SiGooglesheets />}
              <Text width={'fit-content'}
                      whiteSpace="pre-wrap" // Preserve whitespace, allow text wrapping
                      wordBreak="break-word"// Break long words to wrap within the container
                      overflow="hidden" >{item.sender}</Text>
                </Box>
                                  {/* // ------------time and downloads-- */}
                                <Box className="flex gap-5 items-center justify-end">
                                      <FaCloudDownloadAlt/>
                                      
                                      <Box className="flex items-center gap-3 ml-6">
                                        <Text ml={'auto'}>{formatTime(Number(item.time))}</Text>
                                        {item.Status=='read'&&<IoCheckmarkDone color="teal"/>}
                                        {item.Status=='send'&&<IoMdCheckmark color="teal"/>}
                                        { item.Status=='loading'&&<Spinner/>}
  
                                      </Box>
                                  </Box>
                              </Box>
                        
  
                       :
                       <Box className="flex flex-col ">
                        
                         <Text width={'fit-content'}
                      whiteSpace="pre-wrap" // Preserve whitespace, allow text wrapping
                      wordBreak="break-word"// Break long words to wrap within the container
                      overflow="hidden" >{item.message}</Text>
                        
                        <Box className="flex items-center gap-3 ml-6">
                          <Text ml={'auto'}>{formatTime(Number(item.time))}</Text>
                          {item.Status=='read'&&<IoCheckmarkDone color="teal"/>}
                          {item.Status=='send'&&<IoMdCheckmark color="teal"/>}
                          { item.Status=='loading'&&<Spinner/>}
  
                        </Box>
  
                       </Box>
                       } 
                        
                        
                        
                        
             
              </Box>
              </Box>
              
              </Box>
              :
              <Box>
                                          <Box 
                    className=" p-2 flex items-center bg-slate-300 text-customBg" 
                    width={'fit-content'}
                    borderRadius={"10px"}
                    maxW={"600px"} 
  
                    >
                     <Box className="flex items-center  gap-2">
  
                        
  {item.type!="text"? 
    
    <Box>
      {/* -----------Audio----------------------- */}
      {item.type=="Audio"&&
      
        <audio src={item.url} controls />
        
      
  
      }
      {/* -----------Video----------------------- */}
      {
        item.type.startsWith("video/")&&
        
          <video src={item.url} width={'full'} height={'full'} controls/>
          
        }
  
      {/* --------------------imges------ */}
      {
        item.type.startsWith("image/")&&
        
          <Image src={item.url} width={'full'}/>
          
        }
  {/* -----------------------------files---------------- */}
  <Box className="flex items-center">
  {(item.type === "application/vnd.ms-powerpoint" || item.type === "application/vnd.openxmlformats-officedocument.presentationml.presentation") && <BsFillFileEarmarkPptFill />}
  {/* pdf logo */}
  {item.type === "application/pdf" && <FaFilePdf size={"40px"} color="red" />}
  {/* text logo */}
  {item.type === "text/plain" && <IoDocumentTextSharp size={"40px"} color="red" />}
  {/* word logo */}
  {(item.type === "application/msword" || item.type === "application/vnd.openxmlformats-officedocument.wordprocessingml.document") && <FaFileWord />}
  {/* sheets */}
  {(item.type === "application/vnd.ms-excel" || item.type === "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") && <SiGooglesheets />}
  <Text width={'fit-content'}
  whiteSpace="pre-wrap" // Preserve whitespace, allow text wrapping
  wordBreak="break-word"// Break long words to wrap within the container
  overflow="hidden" >{item.message}</Text>
  </Box>
        {/* // ------------time and downloads-- */}
      <Box className="flex gap-5 items-center justify-end">
            <FaCloudDownloadAlt  size={"20px"}/>
            <Text>{formatTime(Number(item.time))}</Text>
        </Box>
  
    </Box>
  
  
  :
  <Box className="flex flex-col ">
  
  <Text width={'fit-content'}
  whiteSpace="pre-wrap" // Preserve whitespace, allow text wrapping
  wordBreak="break-word"// Break long words to wrap within the container
  overflow="hidden" >{item.message}</Text>
  
  <Text ml={'auto'}>{formatTime(Number(item.time))}</Text>
  </Box>
  } 
  
  
  
  
  
  </Box>
                      
                    </Box>
  
             </Box>
            }
                      
                      <div ref={messagesEndRef} />
            </Box>
          ))}
        </Box>
              
  {/* ---------------------MEsssage Footer------------------------- */}
          <Box className="bg-customwhite rounded-b-[50px]">
          <div className="w-full">
          <div className="hidden md:flex gap-3 p-4 items-center">
  
            {showEmojiPicker&&<Box className="absolute bottom-24 z-40" left={"470px"}>
                   <EmojiPicker onEmojiClick={handleEmojiClick}/>
            </Box>}
            <Box cursor={'pointer'}>
            <FaRegFaceGrin size={"30px"} className="text-gray-500" onClick={()=>setShowEmojiPicker(!showEmojiPicker)} />
            </Box>
                   
                            
                            <Menu>
                                <MenuButton as={Button} borderRadius={"50px"}
                                _hover={"none"}
                                _focus={"none"}
                                _active={"none"}
                                bg={'transparent'}
                                >
                                  <MdOutlineAttachFile size={"30px"} className=" text-gray-500 bg-transparent" />
                                </MenuButton>
                                  
                               
                                <MenuList>
                                  <MenuItem className="flex gap-2">
                                  <IoDocumentText size={"25px"} className="text-custompurple"/>
                                  <label htmlFor="Documentinput">
                                    Document
                                    </label>
                                    </MenuItem>
  
                                  <MenuItem className="flex gap-2">
                                  <IoMdPhotos size={"25px"} className="text-blue-600"/>
                                  <label htmlFor="imageinput">
                                    Photos</label></MenuItem>
                                  <MenuItem className="flex gap-2">
                                    <IoIosVideocam size={"25px"} className="text-blue-600"/>
                                  <label htmlFor="videoinput">
                                    Videos
                                  </label>
                                    </MenuItem>
                                  <MenuItem  className="flex gap-2">
                                  <FaCamera size={"22px"} className="text-red-500"/>
                                  <label htmlFor="camerainput">
                                  Camera</label></MenuItem>
                                  <MenuItem>
                                  
                                  Attend a Workshop</MenuItem>
                                </MenuList>
                              </Menu>
                              <Input type="file" name='file' 
                              onChange={(e)=>{Documentinput(e)}} display={'none'} 
                              id="imageinput"
                              accept="image/*"
                              />
                              <Input type="file" name='file' 
                              onChange={(e)=>{Documentinput(e)}} display={'none'} 
                              id="videoinput"
                              accept="video/*"
                              />
  
                              <Input type="file" name='file' 
                              onChange={(e)=>{Documentinput(e)}} display={'none'} 
                              id="Documentinput"
                              accept=".pdf,.txt,.xlsx,.xls,.doc,.docx,.odt"
                              />
  
                        <Input type="text" 
                                
                          placeholder="Type a message"
                          border={'none'}
                          fontSize={'larger'}
                          bg={'white'}
                          _focus={{ boxShadow: 'none', outline: 'none', border: 'none' }}
                          value={msg}
                          onChange={ handleTextInput }
                          
                          onKeyDown={handleKeyDown}
                            />
  
                           {/* <IoSend onClick={sendmsg}/> */}
                          <Box cursor={'pointer'}>
                          <IoIosMic size={"30px"} className=" text-gray-500 bg-transparent"/>
                          </Box>
                           
                           {/* <AudioRecorder 
                           id={conversationId+Date.now()}
              senderId={senderId}
              reciverId={reciverId}
              conversationId={conversationId}
              type={"Audio"}
              message={msg} 
              /> */}
                            
            </div>
          </div>
          </Box>
  
  
      </Box>
  
      
      </>
    )
  }
  
  export default ChatmsgDrawer