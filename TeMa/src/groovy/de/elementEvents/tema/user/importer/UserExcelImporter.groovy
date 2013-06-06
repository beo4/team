package de.elementEvents.tema.user.importer;


import java.util.Map;

import org.aspectj.weaver.ResolvedType.SuperClassWalker;
import org.grails.plugins.excelimport.AbstractExcelImporter;
import org.grails.plugins.excelimport.ExcelImportService;
import org.grails.plugins.excelimport.ExpectedPropertyType;

public class UserExcelImporter extends AbstractExcelImporter {
	
    static Map CONFIG_USER_COLUMN_MAP_G1 = [
        sheet:'Gruppe 1',
        startRow: 1,
        columnMap:  [
                'K':'company',
                'L':'companyadd',
                'A':'salutation',
                'B':'title',
                'C':'firstname',
                'D':"middlename",
                'E':'lastname',
                'G':'street',
                'I':'city',
                'H':'plz',
                'S':'email',
                'J':'mobile',
                'Q':'arrivalDate',
                'R':'departureDate'
        ]
    ]
    
    
    static Map CONFIG_USER_COLUMN_MAP_G2 = [
        sheet:'Gruppe 2',
        startRow: 1,
        columnMap:  [
                'K':'company',
                'L':'companyadd',
                'A':'salutation',
                'B':'title',
                'C':'firstname',
                'D':"middlename",
                'E':'lastname',
                'G':'street',
                'I':'city',
                'H':'plz',
                'S':'email',
                'J':'mobile',
                'Q':'arrivalDate',
                'R':'departureDate'
        ]
    ]
    
    static Map CONFIG_USER_COLUMN_MAP_G3 = [
        sheet:'Gruppe 3',
        startRow: 1,
        columnMap:  [
                'K':'company',
                'L':'companyadd',
                'A':'salutation',
                'B':'title',
                'C':'firstname',
                'D':"middlename",
                'E':'lastname',
                'G':'street',
                'I':'city',
                'H':'plz',
                'S':'email',
                'J':'mobile',
                'Q':'arrivalDate',
                'R':'departureDate'
        ]
    ]
	static Map CONFIG_USER_COLUMN_MAP_G4 = [
        sheet:'Gruppe 4',
        startRow: 1,
        columnMap:  [
                'K':'company',
                'L':'companyadd',
                'A':'salutation',
                'B':'title',
                'C':'firstname',
                'D':"middlename",
                'E':'lastname',
                'G':'street',
                'I':'city',
                'H':'plz',
                'S':'email',
                'J':'mobile',
                'Q':'arrivalDate',
                'R':'departureDate'
        ]
    ]
    
    static Map CONFIG_USER_COLUMN_MAP_G5 = [
        sheet:'Gruppe 5',
        startRow: 1,
        columnMap:  [
                'K':'company',
                'L':'companyadd',
                'A':'salutation',
                'B':'title',
                'C':'firstname',
                'D':"middlename",
                'E':'lastname',
                'G':'street',
                'I':'city',
                'H':'plz',
                'S':'email',
                'J':'mobile',
                'Q':'arrivalDate',
                'R':'departureDate'
        ]
    ]
    
    static Map CONFIG_USER_COLUMN_MAP_NEU = [
        sheet:'1',
        startRow: 1,
        columnMap:  [
                'A':'salutation',
                'C':'firstname',
                'D':'lastname',
                'H':'street',
                'K':'city',
                'J':'plz',
                'B':'company',
                'N':'email',
                'F':'arrivalDate',
                'G':'departureDate',
                'L':'mobile'
        ]
    ]
    
    static Map propertyConfigurationMap = [
        company:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        firstname:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        lastname:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        firstname:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        companyadd:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        street:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        streetnumber:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        city:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        plz:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        email:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        mobile:([expectedType: ExpectedPropertyType.StringType, defaultValue:null]),
        departureDate:([expectedType: ExpectedPropertyType.DateJavaType, defaultValue:null]),
        arrivalDate:([expectedType: ExpectedPropertyType.DateJavaType, defaultValue:null]),
    ]
	
	def getExcelImportService() {
		ExcelImportService.getService()
	}
	
	
	public UserExcelImporter(filename){
		super(filename)
	}
	
	List<Map> getUser() {
		List userList = excelImportService.columns(workbook, CONFIG_USER_COLUMN_MAP_NEU, null, propertyConfigurationMap)
	  }
}
