class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"registration")
		"500"(view:'/error')
        "/faq"(view:"/faq/faq")
        "/download"(controller:"registration",action:"download")
        "/ng-templates/registration/chooseMarketplace"(view:"/marketplace/_chooseMarketplace")
	}
}
