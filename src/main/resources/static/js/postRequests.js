function addNewStudent() {
    var model;
    model = {
        username: $("#username").val(),
        surname: $("#surname").val(),
        email: $("#email").val(),
        password: $("#password").val(),
        year_of_studies: $("#year_of_studies").val(),
        name_of_studies: $("#name_of_studies").val(),
        index_number: $("#index_number").val()
    };
    console.log("post data " + model);
    $.ajax({
        type:"POST",
        url:"/admin/addStudent",
        headers: {
            "Content-Type":"application/json",
            "Accept":"application/json"
        },
        data: JSON.stringify(model),
        success: function(data){
            console.log("!!!!POST RESPONSE " + data);
        },
        error:function(jqXHR,textStatus,errorThrown){
        }
    });
}