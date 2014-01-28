


<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="user.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${userInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="user.email.label" default="Email" />
		
	</label>
	<g:field type="email" name="email" value="${userInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstname', 'error')} ">
	<label for="firstname">
		<g:message code="user.firstname.label" default="Firstname" />
		
	</label>
	<g:textField name="firstname" value="${userInstance?.firstname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastname', 'error')} ">
	<label for="lastname">
		<g:message code="user.lastname.label" default="Lastname" />
		
	</label>
	<g:textField name="lastname" value="${userInstance?.lastname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'language', 'error')} ">
	<label for="language">
		<g:message code="user.language.label" default="Language" />
		
	</label>
	<g:select id="language" name="language.id" from="${de.elementEvents.tema.event.EventLanguage.list()}" optionKey="id" value="${userInstance?.language?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'event', 'error')} ">
	<label for="event">
		<g:message code="user.event.label" default="Event" />
		
	</label>
	<g:select id="event" name="event.id" from="${de.elementEvents.tema.event.Event.list()}" optionKey="id" value="${userInstance?.event?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="user.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${userInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="user.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${userInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="user.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${userInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="user.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${userInstance?.passwordExpired}" />
</div>

