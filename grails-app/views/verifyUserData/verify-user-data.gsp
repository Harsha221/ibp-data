<%--
  Created by IntelliJ IDEA.
  User: dell
  Date: 03-06-2024
  Time: 10:44
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="login"/>
    <title>IBP User Login</title>
    %{--<style>
        .form-group {
            width: 100%;
            /*border: 1px dashed red;*/
        }
        .otp-input-container {
            display: flex;
            /*flex-wrap: nowrap;*/
            /*border: 1px solid black;*/
            width: 100%;
            justify-content: space-between;
            align-items: center;
        }
        .otp-input {
            align-items: center;
            width: 40px;
            height: 40px;
            font-size: 18px;
            text-align: center;
            /*margin: 0 20px;*/
            border: 1px solid #ccc;
            border-radius: 4px;
            -moz-appearance: textfield; /* Firefox */
        }
        /*.otp-input::-webkit-outer-spin-button,*/
        /*.otp-input::-webkit-inner-spin-button {*/
        /*    -webkit-appearance: none;*/
        /*    margin: 0; !* Safari and Chrome *!*/
        /*}*/
        .otp-input:focus {
            border-color: #f00;
            outline: none;
        }
        /* For older Firefox */
        input[type=number] {
            -moz-appearance: textfield;
        }
        @media (max-width: 1199px) {
            .otp-input {
                width: 35px;
                height: 35px;
                font-size: 18px;
                text-align: center;
                border: 1px solid #ccc;
                border-radius: 4px;
                /*-moz-appearance: textfield; !* Firefox *!*/
            }
        }
        @media (max-width: 991px) {
            .otp-input {
                width: 40px;
                height: 40px;
                /*margin: 0 13.421px;*/
                font-size: 18px;
                text-align: center;
                border: 1px solid #ccc;
                border-radius: 4px;
                -moz-appearance: textfield; /* Firefox */
            }
        }
        @media (max-width: 767px) {
            .otp-input {
                width: 40px;
                height: 40px;
                /*margin: 0 13.421px;*/
                font-size: 18px;
                text-align: center;
                border: 1px solid #ccc;
                border-radius: 4px;
                -moz-appearance: textfield; /* Firefox */
            }
        }
    </style>--}%
</head>

<body>

<div class="col-md-4 col-md-offset-4 col-sm-8 col-sm-offset-2 col-xs-10 col-xs-offset-1 login-form">
    <div class="panel-header">
        <h2 class="text-center">
            <g:img src="img/headerIBPlogo.svg" alt="IBP"/>
        </h2>
    </div>

    <div class="panel-body">
        <div class="row">
            <div class="col-xs-12">
                <g:if test='${flash.message}'>
                    <div class="alert alert-danger">${flash.message}</div>
                </g:if>
                <g:if test="${flash.messages}">
                    <div class="alert alert-success">${flash.messages}</div>
                </g:if>
                <g:form controller="verifyUserData" action="otpVerification" method="post" class="login_validator">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-fw ti-user"></i></span>
                            <input type="text" class="form-control" name="${usernameParameter ?: 'phoneNumber'}"
                                   placeholder="Phone Number" id="phoneNumber" value="${params?.phoneNumber}" />
                        </div>
                    </div>

                    <div class="form-group" id="otpDiv" style="display: none">
                        <div class="input-group otp-input-container">
                            <span class="input-group-addon otp-input"><i class="fa fa-fw ti-key"></i></span>
                            <input type="password" class="form-control" name="${passwordParameter ?: 'otp'}"
                                   id="otp" placeholder="OTP"/>
%{--                            <input type="number" maxlength="1" class="otp-input" id="otp1" oninput="moveToNext(this, 'otp2')">--}%
%{--                            <input type="number" maxlength="1" class="otp-input" id="otp2" oninput="moveToNext(this, 'otp3')">--}%
%{--                            <input type="number" maxlength="1" class="otp-input" id="otp3" oninput="moveToNext(this, 'otp4')">--}%
%{--                            <input type="number" maxlength="1" class="otp-input" id="otp4" oninput="moveToNext(this, 'otp5')">--}%
%{--                            <input type="number" maxlength="1" class="otp-input" id="otp5" oninput="moveToNext(this, 'otp6')">--}%
%{--                            <input type="number" maxlength="1" class="otp-input" id="otp6">--}%
                        </div>
                    </div>
                    <div class="form-group">
                        <input id="confirmOtp" type="submit" value="Confirm OTP" class="btn btn-ibp-login btn-block"
                               style="display: none" />
                    </div>
                </g:form>
                <div class="form-group">
                    <input id="sendOtp" type="submit" value="Send OTP" class="btn btn-ibp-login btn-block"
                           onclick="enableOtpInput()" />
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    function enableOtpInput() {
        let target = document.getElementById('otpDiv')
        let sendOtp = document.getElementById('sendOtp')
        let confirmOtp = document.getElementById('confirmOtp')
        target.addEventListener("click", function (event) {
            event.preventDefault()
        })
        target.style.display = 'flex'
        sendOtp.style.display = 'none'
        confirmOtp.style.display = 'block'
    }
    // function moveToNext(current, nextFieldID) {
    //     if (current.value.length >= 1) {
    //         document.getElementById(nextFieldID).focus();
    //     }
    // }
    //
    // function verifyUserData(event) {
    //     event.preventDefault();
    //     const otp = [
    //         document.getElementById('otp1').value,
    //         document.getElementById('otp2').value,
    //         document.getElementById('otp3').value,
    //         document.getElementById('otp4').value,
    //         document.getElementById('otp5').value,
    //         document.getElementById('otp6').value,
    //     ].join('');
    //
    //     console.log(otp)
    //
    //     fetch('/verifyUserData/otpVerification', {
    //         method: 'POST',
    //         headers: {
    //             'Content-Type': 'application/json'
    //         },
    //         body: JSON.stringify({ otp })
    //     })
    //         .then(response => response.json())
    //         .then(data => {
    //             console.log(data);
    //             alert('OTP submitted successfully!');
    //         })
    //         .catch(error => {
    //             console.error('Error:', error);
    //             alert('Failed to submit OTP.');
    //         });
    // }
</script>

</body>
</html>
