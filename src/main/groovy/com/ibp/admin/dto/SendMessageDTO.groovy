package com.ibp.admin.dto

import groovy.transform.builder.Builder
import groovy.util.logging.Slf4j
import io.micronaut.core.annotation.Introspected

@Slf4j
@Introspected
@Builder(prefix = 'with', builderMethodName = 'builder', buildMethodName = 'build')
class SendMessageDTO {
    // Username that is to be used for submission
    String senderMobileNo
    // password (API Key or Login Password)
    String password
    String message
    String toNumber
    // Sender Id to be used for submitting the message
    String senderId
    String templateId
    String peId

    SendMessageDTO() {}

    SendMessageDTO(String senderMobileNo, String password, String message, String toNumber, String senderid, String templateId, String peId) {
        this.senderMobileNo = senderMobileNo;
        this.password = password;
        this.message = message;
        this.toNumber = toNumber;
        this.senderId = senderid;
        this.templateId = templateId;
        this.peId = peId;
    }

    private void submitMessage() {
        try {
// Url that will be called to submit the message
            URL sendUrl = new URL("https://www.smsidea.co.in/smsstatuswithid.aspx");
            HttpURLConnection httpConnection = (HttpURLConnection) sendUrl.openConnection();
// This method sets the method type to POST so that will be send as a POST request.
            httpConnection.setRequestMethod("POST");
// This method is set as true wince we intend to send input to the server.
            httpConnection.setDoInput(true);
// This method implies that we intend to receive data from server.
            httpConnection.setDoOutput(true);
// Implies do not use cached data
            httpConnection.setUseCaches(false);
// Data that will be sent over the stream to the server.
            DataOutputStream dataStreamToServer = new DataOutputStream(httpConnection.getOutputStream());
            dataStreamToServer.writeBytes("mobile="
                    + URLEncoder.encode(this.senderMobileNo, "UTF-8") + "&pass="
                    + URLEncoder.encode(this.password, "UTF-8") + "&senderid="
                    + URLEncoder.encode(this.senderId, "UTF-8") + "&to="
                    + URLEncoder.encode(this.toNumber, "UTF-8") + "&msg="
                    + URLEncoder.encode(this.message, "UTF-8") + "&templateid="
                    + URLEncoder.encode(this.templateId, "UTF-8") + "&peid="
                    + URLEncoder.encode(this.peId, "UTF-8") + "&restype=json");
            dataStreamToServer.flush();
            dataStreamToServer.close();
            // Here take the output value of the server.
            BufferedReader dataStreamFromUrl = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            String dataFromUrl = "", dataBuffer = "";
            // Writing information from the stream to the buffer
            while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
                dataFromUrl += dataBuffer;
            }
            /**
             * Now dataFromUrl variable contains the Response received from the
             * server so we can parse the response and process it accordingly.
             */
            dataStreamFromUrl.close();
            System.out.println("Response: " + dataFromUrl);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*static void main(String[] args) {
        try {
            String message = "Dear user, 222230 is OTP for your login at IBPHub, This code expires in 30 seconds. Never share OTP with anyone. Ameya Information Ltd."
            // Below example is for sending Plain text
//            SendMessageDTO s = new SendMessageDTO('9825217616',
//                    '579608280c4b4f6092107b252d3d03f8', message, "+919106687565",
//                    "IBPHUB", '1207162859991188993', '1201159886408760288');

            SendMessageDTO s = new SendMessageDTO('9825217616',
                    '579608280c4b4f6092107b252d3d03f8', message, "+918780514194",
                    "IBPHUB");
            println('SENDING MESSAGE...!!!!!!!')
            s.submitMessage();
        } catch (Exception ex) {
            ex.printStackTrace()
        }
    }*/
}
