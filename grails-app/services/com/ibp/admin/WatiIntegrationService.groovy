package com.ibp.admin

import com.ibp.admin.dto.WatiVendorBusinessDetailsDto
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper
import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

@Transactional
class WatiIntegrationService {

//    String watiApiUrl = 'https://live-mt-server.wati.io/318155/'
//    String apiKey = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiIxNmZlMDU2Zi1hMzliLTQ1ZGYtOTc2YS01MWI2M2E0Y2I3YWQiLCJ1bmlxdWVfbmFtZSI6ImFrYXNoLnByYWphcGF0aUB2bmhpbmZvdGVjaC5pbiIsIm5hbWVpZCI6ImFrYXNoLnByYWphcGF0aUB2bmhpbmZvdGVjaC5pbiIsImVtYWlsIjoiYWthc2gucHJhamFwYXRpQHZuaGluZm90ZWNoLmluIiwiYXV0aF90aW1lIjoiMDUvMjcvMjAyNCAwNjowNzowMCIsImRiX25hbWUiOiJtdC1wcm9kLVRlbmFudHMiLCJ0ZW5hbnRfaWQiOiIzMTgxNTUiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBRE1JTklTVFJBVE9SIiwiZXhwIjoyNTM0MDIzMDA4MDAsImlzcyI6IkNsYXJlX0FJIiwiYXVkIjoiQ2xhcmVfQUkifQ.FAF_gQ-ZOwv_hyev9jrbMSzryuM1cvQW2Fiv45Wt-IM'
    @Value('${ibp.wati.io.api-url}')
    private String watiApiUrl
    @Value('${ibp.wati.io.auth-token}')
    String apiKey

    def addContact(WatiVendorBusinessDetailsDto vendorBusinessDetails, String phoneNumber) {

        log.info "[addContact] Entered"

        RestTemplate restTemplate = new RestTemplate()
        def requestUrl = "${watiApiUrl}api/v1/addContact/${phoneNumber}"

        def requestBody = [
                name: vendorBusinessDetails.name,
                customParams: vendorBusinessDetails.customParams
        ]
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.set("Authorization", "${apiKey}")

        HttpEntity<String> entity = new HttpEntity<>(JsonOutput.toJson(requestBody), headers)
        def response = restTemplate.postForEntity(requestUrl, entity, String)

        if (response.statusCode == HttpStatus.OK) {
            log.info "[addContact] Exited"
            return new JsonSlurper().parseText(response.body)
        } else {
            log.error "[addContact] status code is not 200 :: Exited :: response.statusCode = ${response.statusCode}"
            throw new RuntimeException("Failed to add contact: ${response.statusCode}")
        }
    }
/*
    def addContact(String name, String phoneNumber) {
        RestTemplate restTemplate = new RestTemplate()
        def requestUrl = "${watiApiUrl}api/v1/addContact/${phoneNumber}"

        def customParams = new ArrayList<CustomParamsDTO>()
        def customParamsDTO = new CustomParamsDTO()
        customParamsDTO.setName("email")
        customParamsDTO.setValue("ajey.prajapati@vnhinfotech.in")
        customParams.add(customParamsDTO)

        def requestBody = [
                name: name,
                customParams: customParams
//                phone_number: phoneNumber
        ]
        def headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        headers.set("Authorization", "Bearer ${apiKey}")

        HttpEntity<String> entity = new HttpEntity<>(JsonOutput.toJson(requestBody), headers)
        def response = restTemplate.postForEntity(requestUrl, entity, String)

        if (response.statusCode == HttpStatus.OK) {
            return new JsonSlurper().parseText(response.body)
        } else {
            throw new RuntimeException("Failed to add contact: ${response.statusCode}")
        }
    }
*/
}
