function runGet() {
    $.ajax({
        url:"/getData",
        type:"GET",
        success: function(data) {
            $.each(data,function (index,list) {
                console.log(list.id +" "+ list.firstName);
                document.getElementById("podmien").innerHTML=list.id;
            });
            /* console.log(data.id);
             console.log(data.firstName);
             document.getElementById("podmien").innerHTML = data;*/
        },error:function(jqXHR,textStatus,errorThrown) {
        }
    });
}
/*$('#buttonDemo4').click(*/function getmyNewJSON() {
    $.ajax({
        url: "/getData",
        type: "GET",
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            $.each(data,function (index, data) {
                console.log(data.id);
                console.log(data.firstName);
                data += '<br/>id' + data.id;
                data += '<br/>id' + data.firstName;
                data += '<br/>----------';
            })
            $('#result4').html(data);
        }
    });
}
/*});*/

function runPost() {
    var model;
    model = {
        id: $("#demo_id").val(),
        firstName: $("#demo_firstName").val()
    };
    console.log("post data " + model);
    $.ajax({
        type:"POST",
        url:"/postData",
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data: JSON.stringify(model),
        success: function(data){
            console.log("!!!!POST RESPONSE " + data);
            document.getElementById("podmien2").innerHTML= "podmienio";
        },
        error:function(jqXHR,textStatus,errorThrown){
        }
    });
}
$(function doThis() {
    console.log("Wyswietlam metode console");
});