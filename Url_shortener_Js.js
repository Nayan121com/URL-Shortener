// User INput the Long Url
// The user input will be sent to the Server
// And After Adding it to the server we need to expose it to Ui again.(do not directly show it to UI get it from the Model)
//
let longUrls = [];

function UrlShortenerModel() {
  var currLongUrl = "";

  var urlData = [];
  var subscribers = [];

  this.getLongUrl = function () {
    return currLongUrl;
  };

  this.setLongUrl = function (val) {
    currLongUrl = val;
  };

  this.getSubscribers = function () {
    return subscribers;
  };

  this.subscriber = function (callBackFn) {
    subscribers.push(callBackFn);
  };

  this.triggerChange = function () {
    subscribers.forEach((callBackFn) => callBackFn());
  };

  this.getData = function () {
    return urlData;
  };

  this.addNew = function (val) {
    urlData.push(val);
  };

  // this.toJson = function () {
  //   return {
  //     longUrl: urlData.longUrl,
  //     shortUrl: urlData.shortUrl,
  //   };
}

var id = 0;
UrlShortenerModel.prototype.urlShortener = function () {
  var shortUrl = "www.urlShortener.com/" + id++;
  this.addNew({ longUrl: this.getLongUrl, shortUrl: shortUrl });

  //Now since user inputed the longUrl and shortUrl is calculated then we can show the data to the user again and that we will do by rendering the UI that needs to be updated
  if (typeof this.getSubscribers === "function") {
    this.triggerChange();
  }
};

function UrlShortenerComponent(model) {
  console.log("Inside urlshortenerComponent");
  var $viewEle = (this.viewEle = $(".outputSection"));

  this.render = function () {
    console.log("inside render");
    var data = model.getData();
    var template = `
      ${data
        .map(
          (obj) =>
            `
          <div class="outputUrl">
            <p class="urls">
              <span><strong>LongUrl:</strong></span>${obj.longUrl}<span></span>
            </p>
            <p class="urls">
              <span><strong>ShortUrl:</strong></span>${obj.shortUrl}<span></span>
            </p>
            <div class="removeBtnContainer">
              <button class="urlRemoveBtn">Remove</button>
            </div>
          </div>
          `
        )
        .join("")}
      `;
    console.log(data[0].longUrl);
    console.log(data[0].shortUrl);
    $viewEle.html(template);
  };

  $(".longURLInput").on("input", function () {
    model.setLongUrl($(".longURLInput").val());
  });

  $(".shortUrlBtn").on("click", function () {
    console.log("Button Clicked!!");
    model.urlShortener();
  });
}

$(document).ready(function () {
  window["model"] = new UrlShortenerModel();

  var view = new UrlShortenerComponent(model);
  view.viewEle.appendTo(document.body.main);

  function renderApp() {
    view.render();
  }
  // renderApp();
  model.subscriber(renderApp);
  // model.onLongUrlChange = function () {
  //   $(".longUrlList").append(`<li>${model.getlongUrl()}</li>`);
  // };
});
