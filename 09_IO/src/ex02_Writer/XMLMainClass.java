package ex02_Writer;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLMainClass {

	/*
		XML
		1. eXtensible Markup Language
		2. HTML(표준 마크업 언어)의 확장 버전
		3. 정해진 태그 외 사용자 정의 태그의 사용이 가능하다.
	 */
	
	/*
		목표.
		1. 경로
			C:\storage\product.xml			// product.xml = 문서 전체 (document)
		2. 내용								// dom 처리 방식
			<products>											// e1					//e1의 부모는 문서 전체				
				<products>											//e2
					<model>세탁기</model>								//e3	//<model>세탁기</model> 텍스트 사이에있는 텍스트를(세탁기)  = textContent > text()
					<maker>삼성</maker>									//e3
					<price>100<price>									//e3
				</products>											//e2
			</products>											// e1
			<products>							
				<products>
					<model>냉장고</model>
					<maker>LG</maker>
					<price>200<price>
				</products>							
			</products>
			<products>							
				<products>
					<model>TV</model>
					<maker>삼성</maker>
					<price>300<price>
				</products>							
			</products>
	 */
	
	public static void main(String[] args) {
		
		Map<String, Object> product1 = new HashMap<String, Object>();
		product1.put("model", "세탁기");
		product1.put("maker", "삼성");
		product1.put("price", "100");
		
		Map<String, Object> product2 = new HashMap<String, Object>();
		product2.put("model", "냉장고");
		product2.put("maker", "LG");
		product2.put("price", "200");
		
		Map<String, Object> product3 = new HashMap<String, Object>();
		product3.put("model", "TV");
		product3.put("maker", "삼성");
		product3.put("price", "300");
		
		List<Map<String, Object>> productList = Arrays.asList(product1, product2, product3);
		
		try {
			
			// Document 생성(Document는 XML문서 자체를 의미한다.)
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			document.setXmlStandalone(true); 	// standalone="no" 제거하기
			
			//<product> 태그 : Element 생성
			Element products = document.createElement("products");
			document.appendChild(products);
			
			// productList 순회
			for(Map<String, Object> map : productList) {
				
				// <product> 태그 : Element 생성
				Element product = document.createElement("product");
				products.appendChild(product);
				
				// <model> 태그 : Element 생성
				Element model = document.createElement("model");
				product.appendChild(model);
				model.setTextContent((String)map.get("model"));
				
				// <maker> 태그 : Element 생성
				Element maker = document.createElement("maker");
				product.appendChild(maker);
				maker.setTextContent((String)map.get("maker"));
				
				//<price> 태그 : Element 생성
				Element price = document.createElement("price");
				product.appendChild(price);
				price.setTextContent(map.get("price").toString());	   			// (String)map.get("price") 로 해도된다. 스트링으로 바꾸는 방법.
				
			}
			
			// XML 설정
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			javax.xml.transform.Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty("encoding", "UTF-8");
			transformer.setOutputProperty("indent", "yes");		// indent 들여쓰기 (Tab)
			transformer.setOutputProperty("doctype-public", "yes"); 	// standalone = "no" 제거하																			
			
			//	XML 문서 만들기
			File dir = new File("C:" + File.separator + "storage");
			if(dir.exists() == false) {
				dir.mkdirs();
			}
			File file = new File(dir, "product.xml");
			Source source = new DOMSource(document);
			StreamResult streamResult = new StreamResult(file);
			transformer.transform(source, streamResult);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
