package web.remenber.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.table.records.entity.Records;
import com.table.records.service.RecordsService;

@Controller
@RequestMapping("/remember")
public class RememberController {
	@Autowired
	private RecordsService recordsService;
	
	@RequestMapping("/update")
	public boolean update(HttpServletRequest request,Records records) {
		recordsService.saveOrUpdate(records);
		return true;
	}
	
	@RequestMapping("/delete")
	public boolean delete(HttpServletRequest request,String id) {
		recordsService.deleteRecords(id);
		return true;
	}

}
