package com.mpgiannis.warehousemanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mpgiannis.warehousemanagement.dto.ImportsExportsDto;
import com.mpgiannis.warehousemanagement.entity.ImportsExports;
import com.mpgiannis.warehousemanagement.service.ImportsExportsService;
@RestController
@RequestMapping("/imports_exports")
public class ImportsExportsRestController {
	
private ImportsExportsService importsExportsService;
	
	@Autowired
	public ImportsExportsRestController(ImportsExportsService theImports_ExportsService) {
		importsExportsService = theImports_ExportsService;
	}
	
	@GetMapping("")
	public List<ImportsExportsDto> findAll() {
		List<ImportsExports> list = importsExportsService.findAll();
		return importsExportsService.ImportsExportsListToDtoList(list);
	}


	
	
	@GetMapping("/{imports_exportsId}")
	public ImportsExportsDto getImports_Exports(@PathVariable int imports_exportsId) {
		
		ImportsExports theImportsExports = importsExportsService.findById(imports_exportsId);
		
		if (theImportsExports == null) {
			throw new NoFoundException("Imports_Exports id not found - " + imports_exportsId);
		}
		
		return new ImportsExportsDto(theImportsExports);
	}

	
	@PostMapping("")
	public ImportsExportsDto addImports_Exports(@RequestBody ImportsExportsDto theImportsExportsDto) {
		theImportsExportsDto.setId(0);
		return importsExportsService.save(theImportsExportsDto);
	}
	
	@PutMapping("")
	public ImportsExportsDto updateImports_Exports(@RequestBody ImportsExportsDto theImportsExportsDto) {
		importsExportsService.save(theImportsExportsDto);
		return theImportsExportsDto;
	}
	
	@DeleteMapping("/{imports_exportsId}")
	public String deleteImports_Exports(@PathVariable int imports_exportsId) {	
		ImportsExports tempImports_Exports = importsExportsService.findById(imports_exportsId);
		if (tempImports_Exports == null) {
			throw new NoFoundException("Imports_Exports id not found - " + imports_exportsId);
		}
		
		importsExportsService.deleteById(imports_exportsId);
		
		return "Deleted imports_exports id - " + imports_exportsId;
	}
	/**
@GetMapping("/apothema/{date}/{productid}")
	public int apothema(@PathVariable String date, @PathVariable int productid){
		
		List<ImportsExports> imex = importsExportsService.findapothema(date,productid);
				
		if (imex == null) {
			throw new RuntimeException(" imports_exports not found - ");
		}
		int apothema=0;
		for (ImportsExports temp : imex) {
            if(temp.getReport().getType().equals("import")) {
            	apothema=apothema+temp.getAmount();
            }
            else {
            	apothema=apothema-temp.getAmount();
            }
        }
		
		return apothema;		
	}
	 */
	
	
	
	@GetMapping("/apothema/{productid}")
	public int findApothema(@PathVariable int productid) {
		List<ImportsExports> a =importsExportsService.findbyProduct_id(productid);
		if (a == null) {
			throw new RuntimeException("Imports_Exports productid not found - " + productid);
		}
		int apothema=0;
		for (ImportsExports temp : a) {
            if(temp.getReport().getType().equals("import")) {
            	apothema=apothema+temp.getAmount();
            }
            else {
            	apothema=apothema-temp.getAmount();
            }
        }
		
		return apothema;
	}
	

}
