package com.ibp.admin

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class VendorsServiceSpec extends Specification {

    VendorsService vendorsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Vendors(...).save(flush: true, failOnError: true)
        //new Vendors(...).save(flush: true, failOnError: true)
        //Vendors vendors = new Vendors(...).save(flush: true, failOnError: true)
        //new Vendors(...).save(flush: true, failOnError: true)
        //new Vendors(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //vendors.id
    }

    void "test get"() {
        setupData()

        expect:
        vendorsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Vendors> vendorsList = vendorsService.list(max: 2, offset: 2)

        then:
        vendorsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        vendorsService.count() == 5
    }

    void "test delete"() {
        Long vendorsId = setupData()

        expect:
        vendorsService.count() == 5

        when:
        vendorsService.delete(vendorsId)
        sessionFactory.currentSession.flush()

        then:
        vendorsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Vendors vendors = new Vendors()
        vendorsService.save(vendors)

        then:
        vendors.id != null
    }
}
