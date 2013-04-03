<%@ page import="de.elementEvents.tema.resource.ResourceOption_i18n" %>



<div class="fieldcontain ${hasErrors(bean: resourceOption_i18nInstance, field: 'i18n', 'error')} required">
	<label for="i18n">
		<g:message code="resourceOption_i18n.i18n.label" default="I18n" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="i18n" name="i18n.id" from="${de.elementEvents.tema.event.EventLanguage.list()}" optionKey="id" required="" value="${resourceOption_i18nInstance?.i18n?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceOption_i18nInstance, field: 'language', 'error')} required">
	<label for="language">
		<g:message code="resourceOption_i18n.language.label" default="Language" />
		<span class="required-indicator">*</span>
	</label>
	<g:localeSelect name="language" value="${resourceOption_i18nInstance?.language}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: resourceOption_i18nInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="resourceOption_i18n.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${resourceOption_i18nInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: resourceOption_i18nInstance, field: 'resourceOption', 'error')} required">
	<label for="resourceOption">
		<g:message code="resourceOption_i18n.resourceOption.label" default="Resource Option" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="resourceOption" name="resourceOption.id" from="${de.elementEvents.tema.resource.ResourceOption.list()}" optionKey="id" required="" value="${resourceOption_i18nInstance?.resourceOption?.id}" class="many-to-one"/>
</div>

