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
        
        	String url = "https://www.mkssoftware.com/apps/support/ProductVersions";
		String product = "";
		String pro_rule = "//div[@class='grid_12']/table/tbody/tr/td[1]";
		String release = "";
		String rel_rule = "//div[@class='grid_12']/table/tbody/tr/td[2]";
		String end_of_support_date = "";
		String eos_date_rule = "//div[@class='grid_12']/table/tbody/tr/td[3]";
		
		HtmlPage EntrancePage = CrawlerUtils.getPage(url);
		
		List <HtmlElement> infoListEle_pro = (List <HtmlElement>)  EntrancePage.getByXPath(pro_rule);
		List <HtmlElement> infoListEle_rel = (List <HtmlElement>)  EntrancePage.getByXPath(rel_rule);
		List <HtmlElement> infoListEle_eos = (List <HtmlElement>)  EntrancePage.getByXPath(eos_date_rule);
		
		for (int i = 0; i < infoListEle_eos.size(); i++) {
			
			product = infoListEle_pro.get(i).getTextContent().trim().replaceAll("\r|\n", "");
			release = infoListEle_rel.get(i).getTextContent().trim().replaceAll("\r|\n", "");
			end_of_support_date = infoListEle_eos.get(i).getTextContent().trim().replaceAll("\r|\n", "");
			System.out.println("[ " + product + " ][ " +  release + " ][ " + end_of_support_date + " ]");
	        if (Files.exists(lifecycle_result_data)) {
		    Files.delete(lifecycle_result_data);
	            Files.createFile(lifecycle_result_data);
	        }
                Files.write(lifecycle_result_data, "\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, product.getBytes(), StandardOpenOption.APPEND);
                Files.write(lifecycle_result_data, "\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, " , ".getBytes(), StandardOpenOption.APPEND);
                Files.write(lifecycle_result_data, "\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, release.getBytes(), StandardOpenOption.APPEND);
                Files.write(lifecycle_result_data, "\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, " , ".getBytes(), StandardOpenOption.APPEND);
                Files.write(lifecycle_result_data, "\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, end_of_support_date.getBytes(), StandardOpenOption.APPEND);
                Files.write(lifecycle_result_data, "\"".getBytes(), StandardOpenOption.APPEND);
	        Files.write(lifecycle_result_data, "\n".getBytes(), StandardOpenOption.APPEND);
		}	

	}
	
}
