let loadGetMsg = () => {
    let nameVar = document.getElementById("name").value;
    fetch("http://www.omdbapi.com/?apikey=f204c1de&t="+nameVar).then(response => response.json())
        .then((response) => {
            document.getElementById("getrespmsg").innerHTML ="";
            let container = document.createElement("div");
            container.innerHTML = "<div class=\"Movie\">"+
                                                  "<h2>"+ response.Title + "</h2>" +
                                                  "<p> Year: " + response.Year + "</p>" +
                                                  "<img src=\"" + response.Poster + "\"/>" +
                                                  "<p> Plot: " + response.Plot + "</p>" +
                                                  "<p> Rated: " + response.Rated + "</p>" +
                                                  "<p> Released: " + response.Released + "</p>" +
                                                  "<p> Runtime: " + response.Runtime + "</p>" +
                                                  "<p> Genre: " + response.Genre + "</p>" +
                                                  "<p> Director: " + response.Director + "</p>" +
                                                  "<p> Writer: " + response.Writer + "</p>" +
                                                  "<p> Actors: " + response.Actors + "</p>" +
                                                  "<p> Language: " + response.Language + "</p>" +
                                                  "<p> Country: " + response.Country + "</p>" +
                                                  "<p> Awards: " + response.Awards + "</p>" +
                                                  "<p> Ratings: " + response.Ratings + "</p>" +
                                                  "<p> Metascore: " + response.Metascore + "</p>" +
                                                  "<p> Type: " + response.Type + "</p>" +
                                                  "<p> BoxOffice: " + response.BoxOffice + "</p>" +
                                                  "<p> Production: " + response.Production + "</p>" +
                                                  "<p> Website: " + response.Website + "</p>" +
                                                  "<p> Response: " + response.Response + "</p>" +
                                                  "</div>\n";
            document.getElementById("getrespmsg").appendChild(container);
            document.getElementById("getrespmsg").classList.remove("display-none");
        })
};

