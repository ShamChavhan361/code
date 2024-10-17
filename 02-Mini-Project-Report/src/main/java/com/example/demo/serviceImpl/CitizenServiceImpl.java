package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import com.example.demo.Entity.CitizenPlan;
import com.example.demo.binding.SearchCriteria;
import com.example.demo.repo.CitizenPlanRepo;
import com.example.demo.service.CitizenPlanService;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;

@Service
public class CitizenServiceImpl implements CitizenPlanService {

	
	@Autowired
	private CitizenPlanRepo repo;
	
	
	@Override
	public List<String> getPlanName() {
		
		return repo.findAllPlanNames();
		
	}

	@Override
	public List<String> getPlanStatus() {
	
		return repo.findAllPlanStatus();
	}

	@Override
	public List<CitizenPlan> searchCitizen(SearchCriteria critetia) {
		
		
		
		//Data filtering
		
		CitizenPlan entity=new CitizenPlan();
		
		if(StringUtils.isNotBlank(critetia.getPlanName()))
		{
			entity.setPlanName(critetia.getPlanName());
		}
		if(StringUtils.isNotBlank(critetia.getPlanStatus()))
		{
			entity.setPlanStatus(critetia.getPlanStatus());
		}
		if(StringUtils.isNotBlank(critetia.getGender()))
		{
			entity.setGender(critetia.getGender());
		}
		if(null != critetia.getPlanStartDate())
		{
			entity.setPlanStartDate( critetia.getPlanStartDate());
		}
		if(null != critetia.getPlanEndDate())
		{
			entity.setPlanEndDate(critetia.getPlanEndDate());
		}
		
		//QBE  (query by example)  create query according to entity object
		
		Example<CitizenPlan> of = Example.of(entity);
		return repo.findAll(of);
	}

	@Override
	public void geneareExcel(HttpServletResponse response) throws IOException {
		
		List<CitizenPlan> records=repo.findAll();
		
		HSSFWorkbook workbook=new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet("Data");
		
		HSSFRow headerRow=sheet.createRow(0);
		
		
		//set data for head row
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Email");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("Plane Name");
		headerRow.createCell(4).setCellValue("Plane Status");
		headerRow.createCell(5).setCellValue("SSN");
		
		int rowIndex=1;
		
		for(CitizenPlan rocord:records)
		{
			HSSFRow dataRow = sheet.createRow(rowIndex);
			
			dataRow.createCell(0).setCellValue(rocord.getName());
			dataRow.createCell(1).setCellValue(rocord.getEmail());
			dataRow.createCell(2).setCellValue(rocord.getGender());
			dataRow.createCell(3).setCellValue(rocord.getPlanStartDate());
			dataRow.createCell(4).setCellValue(rocord.getPlanEndDate());
			dataRow.createCell(5).setCellValue(rocord.getSsn());
			
			
			rowIndex++;
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}

	@Override
	public void generatePdf(HttpServletResponse response) throws IOException {
		
		List<CitizenPlan> records=repo.findAll();
		
		ServletOutputStream outputStream = response.getOutputStream();
		
		Document document = new Document(PageSize.A4);
		
	    PdfWriter.getInstance(document, outputStream);
	    
	    document.open();
		/*
		 * Paragraph p=new Paragraph("Citizen Plan Data"); document.add(p);
		 */
	    
	    PdfPTable table=new PdfPTable(3);  // 3 columns
	    
	    
	    
	    // Styling table headers
        com.lowagie.text.Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        PdfPCell header;
        
        
        
        
        header = new PdfPCell(new Phrase("Name", headerFont));
        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);

        header = new PdfPCell(new Phrase("Email", headerFont));
        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);

        header = new PdfPCell(new Phrase("Gender", headerFont));
        header.setBackgroundColor(Color.LIGHT_GRAY);
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(header);
	    
	    
     // Adding rows dynamically with styling
        com.lowagie.text.Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
        for (CitizenPlan emp : records) {
            PdfPCell cell;

            cell = new PdfPCell(new Phrase(String.valueOf(emp.getName()), cellFont));
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(emp.getEmail(), cellFont));
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(emp.getGender(), cellFont));
            cell.setPadding(5);
            table.addCell(cell);
	   
	
        }
        document.add(table);
        document.close();
	    outputStream.close();
		
	}

}
