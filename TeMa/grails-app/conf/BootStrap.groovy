import org.joda.time.DateTime;

import grails.converters.JSON;
import de.elementEvents.tema.user.Role;
import de.elementEvents.tema.user.Salutation;
import de.elementEvents.tema.user.User;
import de.elementEvents.tema.user.UserRole;

class BootStrap { def springSecurityService

    def init = { servletContext ->
		def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true) 
		def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
		
		def adminUser = User.findByUsername('admin') ?: new User(salutation: Salutation.MR, username: 'admin', password: springSecurityService.encodePassword('admin'), enabled: true).save(failOnError: true)
		
		if (!adminUser.authorities.contains(adminRole)) { UserRole.create adminUser, adminRole}
		
		JSON.registerObjectMarshaller(DateTime) {
			return it?.format("dd.MM.yyyy")
		 }
		
    }
    def destroy = {
    }
}
