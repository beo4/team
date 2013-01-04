package de.elementEvents.tema.event



import org.junit.*
import grails.test.mixin.*

@TestFor(EventLanguageController)
@Mock(EventLanguage)
class EventLanguageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/eventLanguage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.eventLanguageInstanceList.size() == 0
        assert model.eventLanguageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.eventLanguageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.eventLanguageInstance != null
        assert view == '/eventLanguage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/eventLanguage/show/1'
        assert controller.flash.message != null
        assert EventLanguage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/eventLanguage/list'

        populateValidParams(params)
        def eventLanguage = new EventLanguage(params)

        assert eventLanguage.save() != null

        params.id = eventLanguage.id

        def model = controller.show()

        assert model.eventLanguageInstance == eventLanguage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/eventLanguage/list'

        populateValidParams(params)
        def eventLanguage = new EventLanguage(params)

        assert eventLanguage.save() != null

        params.id = eventLanguage.id

        def model = controller.edit()

        assert model.eventLanguageInstance == eventLanguage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/eventLanguage/list'

        response.reset()

        populateValidParams(params)
        def eventLanguage = new EventLanguage(params)

        assert eventLanguage.save() != null

        // test invalid parameters in update
        params.id = eventLanguage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/eventLanguage/edit"
        assert model.eventLanguageInstance != null

        eventLanguage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/eventLanguage/show/$eventLanguage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        eventLanguage.clearErrors()

        populateValidParams(params)
        params.id = eventLanguage.id
        params.version = -1
        controller.update()

        assert view == "/eventLanguage/edit"
        assert model.eventLanguageInstance != null
        assert model.eventLanguageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/eventLanguage/list'

        response.reset()

        populateValidParams(params)
        def eventLanguage = new EventLanguage(params)

        assert eventLanguage.save() != null
        assert EventLanguage.count() == 1

        params.id = eventLanguage.id

        controller.delete()

        assert EventLanguage.count() == 0
        assert EventLanguage.get(eventLanguage.id) == null
        assert response.redirectedUrl == '/eventLanguage/list'
    }
}
