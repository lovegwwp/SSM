package com.jyss.mst.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jyss.mst.agora.media.DynamicKey5;

@Controller
public class AgoraDynamicKeyAction {

	@RequestMapping("/pat/getDynamicKey")
	@ResponseBody
	public Map<String, String> getDynamicKey(@RequestParam("uid") long uid,
			@RequestParam("channel") String channel) {

		HashMap<String, String> m = new HashMap<String, String>();

		String appID = "9eac48dbc0a643aa9c6c1f3d0d2b52cd";
		String appCertificate = "571b65a2ebef45afb2b03c5ae79c3d97";
		// String channel = "7d72365eb983485397e3e3f9d460bdda";
		int ts = (int) (new Date().getTime() / 1000);// 获得系统时间.
		m.put("sjc", ts + "");
		System.out.println("sjc" + ts);
		int r = new Random().nextInt();
		System.out.println("r" + r);
		m.put("ran", r + "");
		// long uid = 2882341273L;
		// int expiredTs = ts + 1800;// 设置半小时过期
		int expiredTs = 0;// 设置半小时过期
		String key = "";
		try {
			key = DynamicKey5.generateMediaChannelKey(appID, appCertificate,
					channel, ts, r, uid, expiredTs);
			// System.out.println("appID=====>" + appID);
			// System.out.println("appCertificate=====>" + appCertificate);
			// System.out.println("channel=====>" + channel);
			// System.out.println("ts=====>" + ts);
			// System.out.println("r=====>" + r);
			// System.out.println("uid=====>" + uid);
			// System.out.println("expiredTs=====>" + expiredTs);
			// System.err.println("key=====>" + key);
			m.put("Dkey", key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;

	}
}
