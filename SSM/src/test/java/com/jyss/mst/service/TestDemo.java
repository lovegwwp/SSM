package com.jyss.mst.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.jyss.mst.entity.Xlsp;

@ContextConfiguration(locations = { "classpath:spring.xml",
		"classpath:SqlMapConfig.xml" })
public class TestDemo extends AbstractJUnit4SpringContextTests {
	@Autowired
	private XlspService spService;

	@Test
	public void test() {
		// XlspService spService = new XlspServiceImpl();
		List<Xlsp> patXlSpList = spService.getXlSpByPat("1", "1", "1", "16",
				"12", "01", "1");
		// Map m = new HashMap();

		if (patXlSpList.size() != 0 && patXlSpList != null) {

			for (Xlsp xlsp : patXlSpList) {
				System.out.println(xlsp.getTitles() + "------------>"
						+ xlsp.getVedio());
			}

			System.out.println("===================================");

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

			for (Xlsp xlsp : patXlSpList) {
				System.out.println(xlsp.getTitles() + "--222------>"
						+ xlsp.getVedio());
			}
		}
	}
}
