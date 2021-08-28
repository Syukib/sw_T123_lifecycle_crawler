package com.crawler.test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class htmlunit_test {
	//for github actions testing
	private static Path lifecycle_result_data = Paths.get("lifecycle_result_data.csv");
	
	public static void main(String[] args) throws IOException {
        	String url = "https://avid.secure.force.com/pkb/articles/en_US/faq/End-of-support-dates";
		String product_model = "";
		String pm_rule = "//span/table/tbody/tr/td[1]";
		String final_sale_date = "";
		String fsd_date_rule = "//span/table/tbody/tr/td[3]";
		String end_of_support_date = "";
		String eos_date_rule = "//span/table/tbody/tr/td[4]";
		
		
		HtmlPage EntrancePage = CrawlerUtils.getPage(url);
		List <HtmlElement> infoListEle_pm = (List <HtmlElement>)  EntrancePage.getByXPath(pm_rule);
		List <HtmlElement> infoListEle_fsd = (List <HtmlElement>)  EntrancePage.getByXPath(fsd_date_rule);
		List <HtmlElement> infoListEle_eos = (List <HtmlElement>)  EntrancePage.getByXPath(eos_date_rule);
		
		for (int i = 0; i < infoListEle_eos.size(); i++) {
			product_model = infoListEle_pm.get(i).getTextContent().trim();
			final_sale_date = infoListEle_fsd.get(i).getTextContent().trim();
			end_of_support_date = infoListEle_eos.get(i).getTextContent().trim();
			System.out.println("[ " + product_model + " ][ " +  final_sale_date + " ][ " + end_of_support_date + " ]");
	        if (!Files.exists(lifecycle_result_data)) {
	            Files.createFile(lifecycle_result_data);
	        }
	        Files.write(lifecycle_result_data, product_model.getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, " , ".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, final_sale_date.getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, " , ".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, end_of_support_date.getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, "\n".getBytes(), StandardOpenOption.APPEND);
		}
		
		String product_model_2 = "";
		String pm_rule_2 = "//span[last()]/table[last()]/tbody/tr/td[1]";
		String final_sale_date_2 = "";
		String fsd_date_rule_2 = "//span[last()]/table[last()]/tbody/tr/td[2]";
		String end_of_support_date_2 = "";
		String eos_date_rule_2 = "//span[last()]/table[last()]/tbody/tr/td[3]";
		
		HtmlPage EntrancePage_2 = CrawlerUtils.getPage(url);
		List <HtmlElement> infoListEle_pm_2 = (List <HtmlElement>)  EntrancePage_2 .getByXPath(pm_rule_2);
		List <HtmlElement> infoListEle_fsd_2 = (List <HtmlElement>)  EntrancePage_2 .getByXPath(fsd_date_rule_2);
		List <HtmlElement> infoListEle_eos_2 = (List <HtmlElement>)  EntrancePage_2 .getByXPath(eos_date_rule_2);
		
		for (int i = 0; i < infoListEle_eos_2.size(); i++) {
			product_model_2 = infoListEle_pm_2.get(i).getTextContent().trim().replaceAll("\r|\n", "");
			final_sale_date_2 = infoListEle_fsd_2.get(i).getTextContent().trim().replaceAll("\r|\n", "");
			end_of_support_date_2 = infoListEle_eos_2.get(i).getTextContent().trim().replaceAll("\r|\n", "");
			System.out.println("[ " + product_model_2 + " ][ " +  final_sale_date_2 + " ][ " + end_of_support_date_2 + " ]");
	        if (!Files.exists(lifecycle_result_data)) {
	            Files.createFile(lifecycle_result_data);
	        }
	        Files.write(lifecycle_result_data, "\"\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, product_model_2.getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, "\"\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, " , ".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, "\"\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, final_sale_date_2.getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, "\"\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, " , ".getBytes(), StandardOpenOption.APPEND);
                Files.write(lifecycle_result_data, "\"\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, end_of_support_date_2.getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, "\"\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, "\n".getBytes(), StandardOpenOption.APPEND);
		}
	}
	
}
