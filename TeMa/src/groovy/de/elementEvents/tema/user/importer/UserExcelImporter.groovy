package de.elementEvents.tema.user.importer;


import org.aspectj.weaver.ResolvedType.SuperClassWalker;
import org.grails.plugins.excelimport.AbstractExcelImporter;

public class UserExcelImporter extends AbstractExcelImporter {
	
	static Map CONFIG_BOOK_COLUMN_MAP = [
             sheet:'Sheet1', 
   			 startRow: 2,
             columnMap:  [
                     'A':'firstname',
                     'B':'lastname',
                     'C':'email',
					 'D':'username',
					 'E':'password',

             ]
     ]
	
	def getExcelImportService() {
		ExcelImportService.getService()
	}
	
	
	public UserExcelImporter(filename){
		super(filename)
	}
	
	List<Map> getUser() {
		List userList = excelImportService.columns(workbook, CONFIG_BOOK_COLUMN_MAP)
	  }
}
