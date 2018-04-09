package com.moyou.moyouRms.easemod;

import com.easemob.server.api.ChatGroupAPI;
import com.easemob.server.api.ChatMessageAPI;
import com.easemob.server.api.ChatRoomAPI;
import com.easemob.server.api.FileAPI;
import com.easemob.server.api.IMUserAPI;
import com.easemob.server.api.SendMessageAPI;
import com.easemob.server.comm.ClientContext;
import com.easemob.server.comm.EasemobRestAPIFactory;

public class Main {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		
		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ChatMessageAPI chat = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
		FileAPI file = (FileAPI)factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
		SendMessageAPI message = (SendMessageAPI)factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
		ChatGroupAPI chatgroup = (ChatGroupAPI)factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		ChatRoomAPI chatroom = (ChatRoomAPI)factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
       /* ResponseWrapper fileResponse = (ResponseWrapper) file.uploadFile(new File("d:/logo.png"));
        String uuid = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("uuid").asText();
        String shareSecret = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("share-secret").asText();
        InputStream in = (InputStream) ((ResponseWrapper) file.downloadFile(uuid, shareSecret, false)).getResponseBody();
        FileOutputStream fos = new FileOutputStream("d:/logo1.png");
        byte[] buffer = new byte[1024];
        int len1 = 0;
        while ((len1 = in.read(buffer)) != -1) {
            fos.write(buffer, 0, len1);
        }
        fos.close();*/
//		 Object ob = user.deleteIMUserBatch(1L);
		Object ob = user.getIMUsersByUserName("asdsa");
		// BF6064E3EAB883D6E03C023814235E62
//		chatgroup.removeSingleUserFromChatGroup("8945545576449", "BF6064E3EAB883D6E03C023814235E62");
		// 8945545576449 BF6064E3EAB883D6E03C023814235E61
		Object o = chatgroup.getChatGroupUsers("8945545576449");
//		System.out.println(o);
	
//		o = chatgroup.addSingleUserToChatGroup("8945545576449", "BF6064E3EAB883D6E03C023814235E62");
//		System.out.println(o);
	}

}
