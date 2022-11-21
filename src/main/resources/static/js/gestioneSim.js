

document.getElementById("attivazione").onclick = function() {

    document.getElementById("codiciAttivazione").style.display="block";
};

document.getElementById("sospensione").onclick = function() {

    document.getElementById("codiciAttivazione").style.display="none";
};

document.getElementById("disattivazione").onclick = function() {

    document.getElementById("codiciAttivazione").style.display="none";
};
//SALVATAGGIO SIM NEL DATABASE
function salvataggioSim() {
    var gestioneSim={
        "IdSim": document.getElementById("ICCID").value,
        "IdRecord": document.getElementById("IdRecord").value,
        "State": document.querySelector('.form-check-input:checked').value,
        "SrvType": document.getElementById("tipoServizio").value,
        "AreaCode": document.getElementById("areaCode").value
    }
    
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/salvataggioSim",
        data: JSON.stringify(gestioneSim),

        success: function (data) {
        console.log("SUCCESS : ", data);
        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });
}

function AttivazioneSim() {

    var gestioneSim={
        "IdSim": document.getElementById("ICCID").value,
        "IdRecord": document.getElementById("IdRecord").value,
        "State": document.querySelector('.form-check-input:checked').value,
        "SrvType": document.getElementById("tipoServizio").value,
        "AreaCode": document.getElementById("areaCode").value
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/SimService",
        data: JSON.stringify(gestioneSim),
        //dataType: "json",
        success: function (data) {
        console.log("SUCCESS : ", data);
            if(data.retCode == 0){
                //if(data.Coverage == 0){
                    //document.getElementById("formIniziale").style.display="none";
                    //document.getElementById("coperto").style.display="block";
                //}else{
                //    document.getElementById("nonCoperto").style.display="block";
                //}
                salvataggioSim();
            }else{
                document.getElementById("errore").style.display="block";
                document.getElementById("erroreMsg").innerHTML=data.retMsg;
            }
        },
        error: function (e) {
        alert("Error!");
        console.log("ERROR: ", e);
        }
    });
}

$( document ).ready(function() {
    console.log( "ready!" );


    if(window.location.pathname.split("/").length > 2){
        document.getElementById("codiciAttivazione").style.display="block";
        document.getElementById("attivazione").checked = true;
        document.getElementById("areaCode").readOnly=true;
    }

    $("#FormAttivazione").submit(function () {
        event.preventDefault();
      
        AttivazioneSim();
      });

});