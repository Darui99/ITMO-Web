window.notify = function(message) {
    $.notify(message, {position: "bottom right"})
};

ajax = function (data, $error) {
    var dict={
        type: "POST",
        url: "",
        dataType: "json",
        success: function (response) {
            if (response["error"]) {
                $error.text(response["error"]);
            } else {
                location.href = response["redirect"];
            }
        }
    };

    for(var key in data) {
        var value = data[key];
        dict[key] = value;
    }

    $.ajax(dict);
};
