package com.jyss.mst.utils;

import java.util.List;

import com.jyss.mst.entity.Xlsp;
import com.jyss.mst.entity.Xlxm;

public class MsgUtil {

	// web前端 视频处理
	public static List<Xlsp> formatList(List<Xlsp> patXlSpList) {
		if (patXlSpList != null && patXlSpList.size() != 0) {

			for (int i = 0; i < patXlSpList.size() - 1; i++) {
				for (int j = i + 1; j < patXlSpList.size(); j++) {
					if ((patXlSpList.get(i).getTitles()).equals(patXlSpList
							.get(j).getTitles())) {
						patXlSpList.get(i).setVedio(
								patXlSpList.get(i).getVedio() + ","
										+ patXlSpList.get(j).getVedio());
						patXlSpList.remove(j);
					}

				}
			}
		}
		return patXlSpList;
	}

	public static List<Xlxm> formatXmList(List<Xlxm> patXlxmList) {
		if (patXlxmList.size() != 0 && patXlxmList != null) {

			for (int i = 0; i < patXlxmList.size() - 1; i++) {
				for (int j = i + 1; j < patXlxmList.size(); j++) {
					if ((patXlxmList.get(i).getTitles()).equals(patXlxmList
							.get(j).getTitles())) {
						patXlxmList.get(i).setVedio(
								patXlxmList.get(i).getVedio() + ","
										+ patXlxmList.get(j).getVedio());
						patXlxmList.remove(j);
					}

				}
			}
		}
		return patXlxmList;
	}

	// 安卓端 训练计划

}
