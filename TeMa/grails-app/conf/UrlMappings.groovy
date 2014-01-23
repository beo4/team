class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller:"registration")
		"500"(view:'/error')
        "/faq"(controller:"registration",action:"faq")
        "/download"(controller:"registration",action:"download")
	}
}
