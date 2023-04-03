define(["postmonger"], function (Postmonger) {
  "use strict";

  var connection = new Postmonger.Session();
  var payload = {};
  var lastStepEnabled = false;
  var steps = [
    // initialize to the same value as what's set in config.json for consistency
    { label: "Step 1", key: "step1" }
  ];
  var currentStep = steps[0].key;
  
  $(window).ready(onRender);

  connection.on("initActivity", initialize);
  connection.on("requestedTokens", onGetTokens);
  connection.on("requestedEndpoints", onGetEndpoints);

  connection.on("clickedNext", onClickedNext);
  connection.on("clickedBack", onClickedBack);
  connection.on("gotoStep", onGotoStep);
  connection.on('requestedInteraction',requestedInteractionHandler);
  
  connection.on('requestedTriggerEventDefinition', onGetEventDefinition);
  connection.on('requestedSchema', onGetSchema);

  function onRender() {
    // JB will respond the first time 'ready' is called with 'initActivity'
    connection.trigger("ready");

    connection.trigger("requestTokens");
    connection.trigger("requestEndpoints");
    connection.trigger('requestSchema');
    
  }

  function initialize(data) {	
	console.log("initialize start ");
	console.log(JSON.stringify(data));
	
    if (data) {
      payload = data;
    }
    
    var payload_name = payload['name'];
	var payload_id = payload['id'];
	
    console.log('payload_name :::'+payload_name);
    console.log('payload_id   :::'+payload_id);
	  
    var hasInArguments = Boolean(
      payload["arguments"] &&
        payload["arguments"].execute &&
        payload["arguments"].execute.inArguments &&
        payload["arguments"].execute.inArguments.length > 0
    );

    var inArguments = hasInArguments
      ? payload["arguments"].execute.inArguments
      : {};

    $.each(inArguments, function (index, inArgument) {
      $.each(inArgument, function (key, val) {
        /*if (key === "message") {
          message = val;
        }*/
        if(key === "phoneNumber") {
			console.log("phoneNumber ::: "+ val);
			//$('input[name=phone]').attr('value',val);
		}else if(key === "message"){
			console.log("message ::: "+val);
			var txt = document.getElementById('textA01');
			txt.value = val;
		}else if(key === "contactIdentifier"){
			console.log("contactIdentifier ::: "+val);
		}
      });
    });

  }

  function onGetTokens(tokens) {
    // Response: tokens = { token: <legacy token>, fuel2token: <fuel api token> }
    // console.log(tokens);
  }

  function onGetEndpoints(endpoints) {
    // Response: endpoints = { restHost: <url> } i.e. "rest.s1.qa1.exacttarget.com"
    // console.log(endpoints);
  }

  function onClickedNext() {
	console.log("!!! next :::");
	
	console.log("number : "+$("#phone").val());
	console.log("text   : "+$("#textA01").val());
	  
	save();
    /*if (
      (currentStep.key === "step3" && steps[3].active === false) ||
      currentStep.key === "step4"
    ) {
      save();
    } else {
      connection.trigger("nextStep");
    }*/
  }

  function onClickedBack() {
    connection.trigger("prevStep");
  }

  function onGotoStep(step) {
    showStep(step);
    connection.trigger("ready");
  }
  
  function requestedInteractionHandler(settings){
    	try{
    		if(settings.triggers == "" || settings.triggers[0].metaData.eventDefinitionKey == undefined){
    			//alert("Data Extension Object 를 선택해 주세요!");
    			alert($("#a_11").text());
    			connection.trigger('destroy');
    		}else{
    			var chkDeConnArr = settings.triggers[0].metaData.eventDefinitionKey.split("-");
    			if(chkDeConnArr[0] != "DEAudience" && chkDeConnArr[0] != "AutomationAud" && chkDeConnArr[0] != "APIEvent"
    				&& chkDeConnArr[0] != "SalesforceObjectTriggerV2" && chkDeConnArr[0] != "CloudPagesSma" 
    					&& chkDeConnArr[0] != "DateEvent" && chkDeConnArr[0] != "ContactAudience"
    						&& settings.triggers[0].metaData.eventDefinitionKey.indexOf("SalesforceObj") != 0){
    				
    				//alert("Data Extension Object 를 선택해 주세요!");
        			alert($("#a_11").text());
        			connection.trigger('destroy');
    				
    			}
    		}
    		eventDefinitionKey = settings.triggers[0].metaData.eventDefinitionKey;
    		jnSettings_name = settings.name;
    		jnVersion = settings.version;
    	}catch(e){
    		console.error(e);
    	}
    }

	function onGetEventDefinition(data) {
        console.log("onGetEventDefinition : " + JSON.stringify(data));
    }
    
    function onGetSchema(data) { // Data Extension 필드 확인가능
    	console.log("onGetSchema : " + JSON.stringify(data));
        
        var exceptionField = ["Subkey","Phone","Date","Send_Date","mobilephone","longUrl","mainCustomerPH"];
        var excptDeField = ["actualChoice"];
        
		personalFieldArr = new Array();

       /* $.each(data.schema, function(index, deData){//DE 필드확인 및 구분
           var key = deData.key;
           var fieldName = key.substring(key.lastIndexOf(".")+1, key.length);
           
           if(key.split(".")[0] == "Event"){
        	   if(excptDeField.indexOf(fieldName) < 0 ){
        		   dataExtensionObj[fieldName] = "{{" + key + "}}";// 저장형태 : { "필드명1" : "{{Event.eventDefinitionKey.필드명1}}" , "필드명2" : "{{Event.eventDefinitionKey.필드명2}}" }   => json 형태로 저장
        	   }
        	   //화면 출력용 개인화 필드 데이터 세팅 전체 필드중 제외 필드 설정
        	   if(exceptionField.indexOf(fieldName) < 0 ){
        		   personalFieldArr.push(fieldName);
        	   }
           }
        });*/
        
    }

  function showStep(step, stepIndex) {
    if (stepIndex && !step) {
      step = steps[stepIndex - 1];
    }
	
	
    currentStep = step;

    $(".step").hide();
    
    connection.trigger("updateButton", {
            button: "next",
            text: "done",
            visible: true,
          });
  }

  function save() {
  	var arrObj = new Array();
	var jObj = new Object();
  	var tDataObj = new Object();
  
	var value = getMessage();
	var phoneNumber = $("#phone").val();
	
	tDataObj.message = getMessage();
	tDataObj.phoneNumber = $("#phone").val();
	
	// 'payload' is initialized on 'initActivity' above.
	// Journey Builder sends an initial payload with defaults
	// set by this activity's config.json file.  Any property
	// may be overridden as desired.
	payload.name = 'mctest_sms';
		
	console.log(JSON.stringify(payload["arguments"]));
	
	jObj.t_data = tDataObj;
    //END t_data 객체
        
    arrObj.push(jObj);
    payload['arguments'].execute.inArguments = arrObj;
    
    payload["arguments"].execute.inArguments = [{ phoneNumber : phoneNumber },{ message : value }];
	
	payload["metaData"].isConfigured = true;
	
	connection.trigger("updateActivity", payload);
  }

  function getMessage() {
    return $("#textA01").val();
  }
});