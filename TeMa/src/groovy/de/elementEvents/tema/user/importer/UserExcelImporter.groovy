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
                'G':'company',
                'H':'salutation',
                'I':'firstname',
                'J':'lastname',
                'K':'companyadd',
                'L':'street',
                'M':'streetnumber',
                'O':'city',
                'N':'plz',
                'R':'email',
                'P':'mobile',
                'S':'arrivalDate',
                'T':'departureDate'
        ]
    ]
    
    static Map CONFIG_USER_COLUMN_MAP_G2 = [
        sheet:'Gruppe 2',
        startRow: 1,
        columnMap:  [
                'G':'company',
                'H':'salutation',
                'I':'firstname',
                'J':'lastname',
                'K':'companyadd',
                'L':'street',
                'M':'streetnumber',
                'O':'city',
                'N':'plz',
                'R':'email',
                'P':'mobile',
                'S':'arrivalDate',
                'T':'departureDate'
        ]
    ]
    
    static Map CONFIG_USER_COLUMN_MAP_G3 = [
        sheet:'Gruppe 3',
        startRow: 1,
        columnMap:  [
                'G':'company',
                'H':'salutation',
                'I':'firstname',
                'J':'lastname',
                'K':'companyadd',
                'L':'street',
                'M':'streetnumber',
                'O':'city',
                'N':'plz',
                'R':'email',
                'P':'mobile',
                'S':'arrivalDate',
                'T':'departureDate'
        ]
    ]
	static Map CONFIG_USER_COLUMN_MAP_G4 = [
        sheet:'Gruppe 4',
        startRow: 1,
        columnMap:  [
                'G':'company',
                'H':'salutation',
                'J':'firstname',
                'K':'lastname',
                'Z':'companyadd',
                'M':'street',
                'N':'streetnumber',
                'P':'city',
                'O':'plz',
                'S':'email',
                'Z':'mobile',
                'I':'title',
                'U':'departureDate',
                'T':'arrivalDate'
        ]
    ]
    
    static Map CONFIG_USER_COLUMN_MAP_G5 = [
        sheet:'Gruppe 5',
        startRow: 1,
        columnMap:  [
                'A':'company',
                'D':'salutation',
                'E':'firstname',
                'F':'lastname',
                'B':'companyadd',
                'K':'email',
                'N':'departureDate',
                'M':'arrivalDate'
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
		List userList = excelImportService.columns(workbook, CONFIG_USER_COLUMN_MAP_G5, null, propertyConfigurationMap)
	  }
}
