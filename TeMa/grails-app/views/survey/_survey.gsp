
<h2>Feedback zur Serviceleiter Konferenz 2016.</h2>
<div class="grey-body">
	<form name="form" class="form-horizontal marketplace">
		<fieldset>
		<legend>
			Ihre Meinung
		</legend>
			<div class="row-fluid">
				<div class="span10 center">
					<div class="control-group"
						data-ng-class="{error: errors.survey.entryValue}">
						<label class="span9" for="title"><g:message encodeAs="HTML" code="user.survey.entryValue"/></label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="entryValue"
								name="entryValue" ng-required="true"
								ng-model="participant.survey.entryValue"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.entryValue">{{errors.survey.entryValue}}</span>
						</div>
					</div>

					<div class="control-group"
						data-ng-class="{error: errors.survey.organisationValue}">
						<label class="span9" for="title"><g:message encodeAs="HTML" code="user.survey.organisationValue"/></label>
						<div class="controls span2">
							<select class="input-block-level" type="text"
								id="organisationValue" name="organisationValue"
								ng-required="true"
								ng-model="participant.survey.organisationValue"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.organisationValue">{{errors.survey.organisationValue}}</span>
						</div>
					</div>

					<div class="control-group"
						data-ng-class="{error: errors.survey.theme}">
						<label class="span9" for="title"><g:message encodeAs="HTML" code="user.survey.overall"/></label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="theme"
								name="theme" ng-required="true"
								ng-model="participant.survey.theme"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.theme">{{errors.survey.theme}}</span>
						</div>
					</div>
					
					<div class="control-group" data-ng-class="{error: errors.survey.themeText}">
						<label class="span9" for="title"><g:message encodeAs="HTML" code="user.survey.theme"/></label>
						<div class="span12">
							<textarea class="span10" rows="5" id="themeText" name="themeText"
								data-ng-model="participant.survey.themeText"></textarea>
							<span class="help-inline" data-ng-show="errors.survey.themeText">{{errors.survey.themeText}}</span>
						</div>
					</div>

					<div class="control-group"
						data-ng-class="{error: errors.survey.marketplace}">
						<label class="span9" for="title"><g:message encodeAs="HTML" code="user.survey.marketplace"/></label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="marketplace"
								name="marketplace" ng-required="true"
								ng-model="participant.survey.marketplace"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.marketplace">{{errors.survey.marketplace}}</span>
						</div>
					</div>
					
					<div class="control-group" data-ng-class="{error: errors.dailyBusinessText}">
						<label class="span9" for="title"><g:message encodeAs="HTML" code="user.survey.marketplaceText"/></label>
						<div class="span12">
							
							<div class="control-group" data-ng-class="{error: errors.aufbauherstellermanagement}">
								<label class="control-label" for="title"><g:message code="user.survey.01.aufbauherstellermanagement"/></label>
								<div class="controls">
									<input type="checkbox" id="aufbauherstellermanagement" name="aufbauherstellermanagement" data-ng-model="participant.survey.aufbauherstellermanagement">
									<span class="help-inline" data-ng-show="errors.aufbauherstellermanagement">{{errors.aufbauherstellermanagement}}</span>
								</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.gewahrleistung}">
								<label class="control-label" for="title"><g:message code="user.survey.02.gewahrleistung"/></label>
								<div class="controls">
									<input type="checkbox" id="careport" name="careport" data-ng-model="participant.survey.gewahrleistung">
									<span class="help-inline" data-ng-show="errors.gewahrleistung">{{errors.gewahrleistung}}</span>
								</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.euromobil}">
									<label class="control-label" for="title"><g:message code="user.survey.03.euromobil"/></label>
									<div class="controls">
										<input type="checkbox" id="euromobil" name="euromobil" data-ng-model="participant.survey.euromobil">
										<span class="help-inline" data-ng-show="errors.euromobil">{{errors.classicparts}}</span>
									</div>
							</div>
														
							<div class="control-group" data-ng-class="{error: errors.careport}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.04.careport"/></label>
									<div class="controls">
										<input type="checkbox" id="careport" name="careport" data-ng-model="participant.survey.careport">
										<span class="help-inline" data-ng-show="errors.careport">{{errors.careport}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.grosskundenbetreuung}">
									<label class="control-label" for="title"><g:message code="user.survey.05.grosskundenbetreuung"/></label>
									<div class="controls">
										<input type="checkbox" id="grosskundenbetreuung" name="grosskundenbetreuung" data-ng-model="participant.survey.grosskundenbetreuung">
										<span class="help-inline" data-ng-show="errors.grosskundenbetreuung">{{errors.grosskundenbetreuung}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.ihvpost}">
									<label class="control-label" for="title"><g:message code="user.survey.06.ihvpost"/></label>
									<div class="controls">
										<input type="checkbox" id="ihvpost" name="ihvpost" data-ng-model="participant.survey.ihvpost">
										<span class="help-inline" data-ng-show="errors.ihvpost">{{errors.ihvpost}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.inspektion}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.07.inspektion"/></label>
									<div class="controls">
										<input type="checkbox" id="inspektion" name="inspektion" data-ng-model="participant.survey.inspektion">
										<span class="help-inline" data-ng-show="errors.inspektion">{{errors.inspektion}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.onlinesz}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.08.onlinesz"/></label>
									<div class="controls">
										<input type="checkbox" id="onlinesz" name="onlinesz" data-ng-model="participant.survey.onlinesz">
										<span class="help-inline" data-ng-show="errors.onlinesz">{{errors.onlinesz}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.originalteile}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.09.originalteile"/></label>
									<div class="controls">
										<input type="checkbox" id="originalteile" name="originalteile" data-ng-model="participant.survey.originalteile">
										<span class="help-inline" data-ng-show="errors.originalteile">{{errors.originalteile}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.partnerbetreuung}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.10.partnerbetreuung"/></label>
									<div class="controls">
										<input type="checkbox" id="partnerbetreuung" name="partnerbetreuung" data-ng-model="participant.survey.partnerbetreuung">
										<span class="help-inline" data-ng-show="errors.partnerbetreuung">{{errors.partnerbetreuung}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.taxi}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.11.taxi"/></label>
									<div class="controls">
										<input type="checkbox" id="taxi" name="taxi" data-ng-model="participant.survey.taxi">
										<span class="help-inline" data-ng-show="errors.taxi">{{errors.taxi}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.servicemarketing}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.12.servicemarketing"/></label>
									<div class="controls">
										<input type="checkbox" id="servicemarketing" name="servicemarketing" data-ng-model="participant.survey.servicemarketing">
										<span class="help-inline" data-ng-show="errors.servicemarketing">{{errors.servicemarketing}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.skpcommerce}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.13.skpcommerce"/></label>
									<div class="controls">
										<input type="checkbox" id="skpcommerce" name="skpcommerce" data-ng-model="participant.survey.skpcommerce">
										<span class="help-inline" data-ng-show="errors.skpcommerce">{{errors.skpcommerce}}</span>
									</div>
							</div>
														
							<div class="control-group" data-ng-class="{error: errors.servicestandards}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.14.servicestandards"/></label>
									<div class="controls">
										<input type="checkbox" id="servicestandards" name="servicestandards" data-ng-model="participant.survey.servicestandards">
										<span class="help-inline" data-ng-show="errors.servicestandards">{{errors.servicestandards}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.servicequalifizierungen}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.15.servicequalifizierungen"/></label>
									<div class="controls">
										<input type="checkbox" id="servicequalifizierungen" name="servicequalifizierungen" data-ng-model="participant.survey.servicequalifizierungen">
										<span class="help-inline" data-ng-show="errors.servicequalifizierungen">{{errors.servicequalifizierungen}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.werkstattsysteme}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.16.werkstattsysteme"/></label>
									<div class="controls">
										<input type="checkbox" id="werkstattsysteme" name="werkstattsysteme" data-ng-model="participant.survey.werkstattsysteme">
										<span class="help-inline" data-ng-show="errors.werkstattsysteme">{{errors.werkstattsysteme}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.leasing}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.17.leasing"/></label>
									<div class="controls">
										<input type="checkbox" id="leasing" name="leasing" data-ng-model="participant.survey.leasing">
										<span class="help-inline" data-ng-show="errors.leasing">{{errors.leasing}}</span>
									</div>
							</div>
														
							<div class="control-group" data-ng-class="{error: errors.classicparts}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.18.classicparts"/></label>
									<div class="controls">
										<input type="checkbox" id="classicparts" name="classicparts" data-ng-model="participant.survey.classicparts">
										<span class="help-inline" data-ng-show="errors.classicparts">{{errors.classicparts}}</span>
									</div>
							</div>
							
							<div class="control-group" data-ng-class="{error: errors.merchandising}">
									<label class="control-label" for="title"><g:message encodeAs="HTML" code="user.survey.19.merchandising"/></label>
									<div class="controls">
										<input type="checkbox" id="merchandising" name="merchandising" data-ng-model="participant.survey.merchandising">
										<span class="help-inline" data-ng-show="errors.merchandising">{{errors.merchandising}}</span>
									</div>
							</div>
							
						</div>
					</div>
					
					<div class="control-group"
						data-ng-class="{error: errors.survey.marketplaceTime}">
						<label class="span9" for="title">Empfanden Sie die Zeit f&uuml;r Gespr&auml;che von Servicethemen als ausreichend?</label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="marketplaceTime"
								name="marketplaceTime" ng-required="true"
								ng-model="participant.survey.marketplaceTime"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.marketplaceTime">{{errors.survey.marketplaceTime}}</span>
						</div>
					</div>
					
					<div class="control-group"
						data-ng-class="{error: errors.survey.dailyBusiness}">
						<label class="span9" for="title">In welchem Ma&szlig;e hilft Ihnen der Inhalt der Themen bei Ihrem t&auml;glichen Gesch&auml;ft weiter/wird weiterhelfen?</label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="dailyBusiness"
								name="dailyBusiness" ng-required="true"
								ng-model="participant.survey.dailyBusiness"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.dailyBusiness">{{errors.survey.dailyBusiness}}</span>
						</div>
					</div>

					<div class="control-group"
						data-ng-class="{error: errors.survey.overallImpression}">
						<label class="span9" for="title">Wie bewerten Sie die Serviceleiter Konferenz 2016 im Ganzen?</label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="overallImpression"
								name="overallImpression" ng-required="true"
								ng-model="participant.survey.overallImpression"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.overallImpression">{{errors.survey.overallImpression}}</span>
						</div>
					</div>
					
					<div class="control-group"
						data-ng-class="{error: errors.survey.overallImpression}">
						<label class="span9" for="title">Inwieweit hat die Serviceleiter Konferenz Ihren Erwartungen entsprochen?</label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="expectation"
								name="expectation" ng-required="true"
								ng-model="participant.survey.expectation"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.expectation">{{errors.survey.expectation}}</span>
						</div>
					</div>
					
					<div class="control-group"
						data-ng-class="{error: errors.survey.reentry}">
						<label class="span9" for="title">Wie wahrscheinlich ist Ihre Teilnahme an der n&auml;chsten Serviceleiter Konferenz?</label>
						<div class="controls span2">
							<select class="input-block-level" type="text" id="reentry"
								name="reentry" ng-required="true"
								ng-model="participant.survey.reentry"
								ng-options="surveyOption.value as surveyOption.text for surveyOption in surveyOptions">
							</select> <span class="help-inline"
								data-ng-show="errors.survey.reentry">{{errors.survey.reentry}}</span>
						</div>
					</div>
					
					<div class="control-group" data-ng-class="{error: errors.other}">
						<label class="span9" for="title">Weitere Anregungen.</label>
						<div class="span12">
							<textarea class="span10" rows="5" id="other" name="other"
								data-ng-model="participant.survey.other"></textarea>
							<span class="help-inline" data-ng-show="errors.survey.other">{{errors.survey.other}}</span>
						</div>
					</div>
					
				</div>
			</div>

		</fieldset>
		
		<div class="form-actions">
			
			<button data-ng-click="updateSurvey(participant)"
				class="btn btn-primary pull-right">
				Absenden<i class="icon-play"></i>
			</button>
		</div>

	</form>
</div>