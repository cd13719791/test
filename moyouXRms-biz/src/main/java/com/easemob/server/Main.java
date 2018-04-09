package com.easemob.server;

import com.easemob.server.api.ChatGroupAPI;
import com.easemob.server.api.ChatMessageAPI;
import com.easemob.server.api.ChatRoomAPI;
import com.easemob.server.api.FileAPI;
import com.easemob.server.api.IMUserAPI;
import com.easemob.server.api.SendMessageAPI;
import com.easemob.server.comm.ClientContext;
import com.easemob.server.comm.EasemobRestAPIFactory;
import com.easemob.server.comm.body.UserCrowNewoOwner;

public class Main {
	// static String[] strs={"9cbbeae9b41d498a85486ee7bb63da52",
	// "4939ca37eb714c1ba6de6a6e6c768f53", "9d6d4867cf684b83a3a1745f5446723d",
	// "f258aa6a15474452a7584e9e1bd21e3a"
	// , "2483daff404d4026b822bc93127664fe", "1f931bc9c4b1461398c9c47e3de131d1",
	// "7a6fdaec7d2242ce9cc2df971911fd54"
	// , "464e1143648641ae9f7e23a5155ccfef", "13bb50e8dcf34cef9bfe0d33cb129453",
	// "8a65027ab6bf48818f28c54a8ef26a73", "d055edf246e746e9a005c112011c4046",
	// "353fef280ce947bcbaaa84526509d72a", "933c77b2d9e3470ebe3c99d4d6b38f0b",
	// "a6d476d3be7e4de39aa2172e4a0b0ae2", "9f1641da7fe8485bbffc1436165622fc",
	// "ef05ee7592ce481d860f57763bd4ed83", "e26b60f267224a359a7a89b791f780cf",
	// "01c11195b74b4f95bb77e13d47627ec5", "0d673b2b80864f15b2401d4e27d1d54c",
	// "228aa9bbe22d41b388345a52f6073b1a", "eae6a3624c304d7a9014e7788ac60a53",
	// "b9e7fbf157e94261a32d8ae554d7efbe", "666188528e39425585a479fc231bd1cc",
	// "825ff2d9337242a0a47aece062585a35", "a04ec89d642d495f98c5487330ae9473",
	// "76942139a25547e783134414bedad6d8", "8b61b8ce4d6342b0ba2d3350ac5571ba",
	// "c45de33fbc1342a4988e72ce6fb0771c", "74093e1f7a6b4255af51da175ed0f79c",
	// "c9892d99021b4fc983f5ec75ad2c1ab4", "d9e54620108340588d0ed2dfab218646",
	// "d4499e3036b94b0486d7b1dcefd1762e"};
	static String[] strs = { "d27fcde1abbb4c3080fb1d85137926e8" };
	@SuppressWarnings({ "all"})
	public static void main(String[] args) throws Exception {
		EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		IMUserAPI user = (IMUserAPI)factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
		ChatMessageAPI chat = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
		FileAPI file = (FileAPI)factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
		SendMessageAPI message = (SendMessageAPI)factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
		ChatGroupAPI chatgroup = (ChatGroupAPI)factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
		ChatRoomAPI chatroom = (ChatRoomAPI)factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
		UserCrowNewoOwner chatGroupBody = new UserCrowNewoOwner("d5253702d16b4c2198c9fc14aee5c769");
		System.out.println(chatgroup.addSingleUserToChatGroup("14007768514562", "d5253702d16b4c2198c9fc14aee5c769"));
//		14007603888131
//		14007647928321
//		14007703502849
//		14007768514562
//		System.out.println(	chatgroup.transferChatGroupOwner("14007603888131",  chatGroupBody));
//		System.out.println(	chatgroup.transferChatGroupOwner("14007647928321",  chatGroupBody));
//		System.out.println(	chatgroup.transferChatGroupOwner("14007703502849",  chatGroupBody));
//		System.out.println(	chatgroup.transferChatGroupOwner("14007768514562",  chatGroupBody));
//	
	
	
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
//		Object ob = user.getIMUserStatus("94093ad137d441d29605f1b99ffa0a07");
//		List<String> list=new ArrayList<String>();
//		Map map1=JsonUtil.toMap(user.getOfflineMsgCount("9aaf8023976247d8bf2ddca4cc8eebf3"));
//		System.out.println(map1);
//		Map map=JsonUtil.toMap(user.getOfflineMsgCount("9aaf8023976247d8bf2ddca4cc8eebf3"));
//		
//		Map map2=JsonUtil.toMap(map.get("responseBody"));
//		String data=map2.get("data").toString().split("=")[1];
//		System.out.println(data.substring(0,data.length()-1));
		
//		for (int i = 0; i < strs.length; i++) {
//			list.add(strs[i]);
//		}
//		try {
//			for (int i = 0; i < list.size(); i++) {
//				System.out.println(chatgroup.addSingleUserToChatGroup("15288733073409", list.get(i).toString()));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//		}
//		chatgroup.g yun xing main fang fa
//		System.out.println(ob);
	}

}
