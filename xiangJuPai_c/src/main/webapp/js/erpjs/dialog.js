var CONTEXTPATH = './';
var isIE = navigator.userAgent.toLowerCase().indexOf("msie") != -1;
var isIE6 = navigator.userAgent.toLowerCase().indexOf("msie 6.0") != -1;
var isIE7 = navigator.userAgent.toLowerCase().indexOf("msie 7.0") != -1 && !window.XDomainRequest;
var isIE8 = !!window.XDomainRequest;
var isGecko = navigator.userAgent.toLowerCase().indexOf("gecko") != -1;
var isQuirks = document.compatMode == "BackCompat";
if (isGecko) {
    var p = HTMLElement.prototype;
    p.__defineSetter__("innerText",
    function(a) {
        this.textContent = a
    });
    p.__defineGetter__("innerText",
    function() {
        return this.textContent
    });
    p.insertAdjacentElement = function(a, b) {
        switch (a) {
        case "beforeBegin":
            this.parentNode.insertBefore(b, this);
            break;
        case "afterBegin":
            this.insertBefore(b, this.firstChild);
            break;
        case "beforeEnd":
            this.appendChild(b);
            break;
        case "afterEnd":
            if (this.nextSibling) {
                this.parentNode.insertBefore(b, this.nextSibling)
            } else {
                this.parentNode.appendChild(b)
            }
            break
        }
    };
    p.insertAdjacentHTML = function(b, d) {
        var c = this.ownerDocument.createRange();
        c.setStartBefore(this);
        var a = c.createContextualFragment(d);
        this.insertAdjacentElement(b, a)
    };
    p.attachEvent = function(b, a) {
        b = b.substring(2);
        this.addEventListener(b, a, false)
    };
    p.detachEvent = function(b, a) {
        b = b.substring(2);
        this.removeEventListener(b, a, false)
    };
    p.__defineGetter__("currentStyle",
    function() {
        return this.ownerDocument.defaultView.getComputedStyle(this, null)
    });
    p.__defineGetter__("children",
    function() {
        var b = [];
        for (var a = 0; a < this.childNodes.length; a++) {
            var c = this.childNodes[a];
            if (c.nodeType == 1) {
                b.push(c)
            }
        }
        return b
    });
    p.__defineSetter__("outerHTML",
    function(b) {
        var a = this.ownerDocument.createRange();
        a.setStartBefore(this);
        var c = a.createContextualFragment(b);
        this.parentNode.replaceChild(c, this);
        return b
    });
    p.__defineGetter__("outerHTML",
    function() {
        var a;
        var b = this.attributes;
        var d = "<" + this.tagName.toLowerCase();
        for (var c = 0; c < b.length; c++) {
            a = b[c];
            if (a.specified) {
                d += " " + a.name + '="' + a.value + '"'
            }
        }
        if (!this.hasChildNodes) {
            return d + ">"
        }
        return d + ">" + this.innerHTML + "</" + this.tagName.toLowerCase() + ">"
    });
    p.__defineGetter__("canHaveChildren",
    function() {
        switch (this.tagName.toLowerCase()) {
        case "area":
        case "base":
        case "basefont":
        case "col":
        case "frame":
        case "hr":
        case "img":
        case "br":
        case "input":
        case "isindex":
        case "link":
        case "meta":
        case "param":
            return false
        }
        return true
    });
    Event.prototype.__defineGetter__("srcElement",
    function() {
        var a = this.target;
        try{
	       	while (a.nodeType != 1) {
	            a = a.parentNode0
	        }
        }
        catch(e){}
        return a
    });
    p.__defineGetter__("parentElement",
    function() {
        if (this.parentNode == this.ownerDocument) {
            return null
        }
        return this.parentNode
    })
} else {
    try {
        document.documentElement.addBehavior("#default#userdata");
        document.execCommand("BackgroundImageCache", false, true)
    } catch(e) {
        alert(e)
    }
}
var _setTimeout = setTimeout;
window.setTimeout = function(f, c, d) {
    var b = Array.prototype.slice.call(arguments, 2);
    var a = function() {
        f.apply(null, b)
    };
    return _setTimeout(a, c)
};
var Core = {};
Core.attachMethod = function(b) {
    if (!b || b["ROMEA"]) {
        return
    }
    if (b.nodeType == 9) {
        return
    }
    var c;
    try {
        if (isGecko) {
            c = b.ownerDocument.defaultView
        } else {
            c = b.ownerDocument.parentWindow
        }
        for (var d in ROMEE) {
            b[d] = c.ROMEE[d]
        }
    } catch(a) {}
};
var Constant = {};
Constant.Null = "_ZVING_NULL";
function ROME(a) {
    if (typeof(a) == "string") {
        a = document.getElementById(a);
        if (!a) {
            return null
        }
    }
    if (a) {
        Core.attachMethod(a)
    }
    return a
}
function ROMEV(b) {
    var a = b;
    b = ROME(b);
    if (!b) {
        alert("表单元素不存在:" + a);
        return null
    }
    switch (b.type.toLowerCase()) {
    case "submit":
    case "hidden":
    case "password":
    case "textarea":
    case "file":
    case "image":
    case "select-one":
    case "text":
        return b.value;
    case "checkbox":
    case "radio":
        if (b.checked) {
            return b.value
        } else {
            return null
        }
    default:
        return ""
    }
}
function ROMES(c, b) {
    var a = c;
    c = ROME(c);
    if (!b && b != 0) {
        b = ""
    }
    if (!c) {
        alert("表单元素不存在:" + a);
        return
    }
    switch (c.type.toLowerCase()) {
    case "submit":
    case "hidden":
    case "password":
    case "textarea":
    case "button":
    case "file":
    case "image":
    case "select-one":
    case "text":
        c.value = b;
        break;
    case "checkbox":
    case "radio":
        if (c.value == "" + b) {
            c.checked = true
        } else {
            c.checked = false
        }
        break
    }
}
function ROMET(d, g) {
    g = ROME(g);
    g = g || document;
    var f = g.getElementsByTagName(d);
    var b = [];
    var a = f.length;
    for (var c = 0; c < a; c++) {
        b.push(ROME(f[c]))
    }
    return b
}
function ROMEN(c) {
    if (typeof(c) == "string") {
        c = document.getElementsByName(c);
        if (!c || c.length == 0) {
            return null
        }
        var a = [];
        for (var b = 0; b < c.length; b++) {
            var d = c[b];
            if (d.getAttribute("ztype") == "select") {
                d = d.parentNode
            }
            Core.attachMethod(d);
            a.push(d)
        }
        c = a
    }
    return c
}
function ROMENV(d) {
    d = ROMEN(d);
    if (!d) {
        return null
    }
    var a = [];
    for (var c = 0; c < d.length; c++) {
        var b = ROMEV(d[c]);
        if (b != null) {
            a.push(b)
        }
    }
    return a.length == 0 ? null: a
}
function ROMENS(f, d) {
    f = ROMEN(f);
    if (!f) {
        return
    }
    if (!f[0]) {
        return ROMES(f, d)
    }
    if (f[0].type == "checkbox") {
        if (d == null) {
            d = new Array(4)
        }
        var a = d;
        if (!isArray(d)) {
            a = d.split(",")
        }
        for (var b = 0; b < a.length; b++) {
            for (var c = 0; c < f.length; c++) {
                if (f[c].checked) {
                    continue
                }
                ROMES(f[c], a[b])
            }
        }
        return
    }
    for (var c = 0; c < f.length; c++) {
        ROMES(f[c], d)
    }
}
function ROMEF(a) {
    if (!a) {
        return document.forms[0]
    } else {
        a = ROME(a);
        if (a && a.tagName.toLowerCase() != "form") {
            return null
        }
        return a
    }
}

function encodeURL(a) {
    return encodeURI(a).replace(/=/g, "%3D").replace(/\+/g, "%2B").replace(/\?/g, "%3F").replace(/\&/g, "%26")
}
function htmlEncode(a) {
    return a.replace(/&/g, "&amp;").replace(/\"/g, "&quot;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/ /g, "&nbsp;")
}
function htmlDecode(a) {
    return a.replace(/\&quot;/g, '"').replace(/\&lt;/g, "<").replace(/\&gt;/g, ">").replace(/\&nbsp;/g, " ").replace(/\&amp;/g, "&")
}
function isInt(a) {
    return /^\-?\d+ROME/.test("" + a)
}
function isNumber(d) {
    var b = "" + d;
    for (var a = 0; a < d.length; a++) {
        var c = d.charAt(a);
        if (c != "." && c != "E" && isNaN(parseInt(c))) {
            return false
        }
    }
    return true
}
function isTime(c) {
    var a = c.split(":");
    if (a.length != 3) {
        return false
    }
    var b = new Date();
    b.setHours(a[0]);
    b.setMinutes(a[1]);
    b.setSeconds(a[2]);
    return b.toString().indexOf("Invalid") < 0
}
function isDate(c) {
    var a = c.split("-");
    if (a.length != 3) {
        return false
    }
    var b = new Date();
    b.setFullYear(a[0]);
    b.setMonth(a[1]);
    b.setDay(a[2]);
    return b.toString().indexOf("Invalid") < 0
}
function isArray(a) {
    if (!a) {
        return false
    }
    if (a.constructor.toString().indexOf("Array") == -1) {
        return false
    } else {
        return true
    }
}
function getEvent(a) {
    return window.event || a
}
function stopEvent(a) {
    a = getEvent(a);
    if (!a) {
        return
    }
    if (isGecko) {
        a.preventDefault();
        a.stopPropagation()
    }
    a.cancelBubble = true;
    a.returnValue = false
}
function cancelEvent(a) {
    a = getEvent(a);
    a.cancelBubble = true
}
function getEventPosition(b) {
    b = getEvent(b);
    var f = {
        x: b.clientX,
        y: b.clientY
    };
    var d;
    if (isGecko) {
        d = b.srcElement.ownerDocument.defaultView
    } else {
        d = b.srcElement.ownerDocument.parentWindow
    }
    var a, c;
    while (d != d.parent) {
        if (d.frameElement) {
            pos2 = ROMEE.getPosition(d.frameElement);
            f.x += pos2.x;
            f.y += pos2.y
        }
        a = Math.max(d.document.body.scrollLeft, d.document.documentElement.scrollLeft);
        c = Math.max(d.document.body.scrollTop, d.document.documentElement.scrollTop);
        f.x -= a;
        f.y -= c;
        d = d.parent
    }
    return f
}
function toXMLDOM(a) {
    var f;
    try {
        try {
            f = new ActiveXObject("Microsoft.XMLDOM")
        } catch(b) {
            f = new ActiveXObject("Msxml2.DOMDocument")
        }
        f.loadXML(a)
    } catch(d) {
        var c = new DOMParser();
        f = c.parseFromString(a, "text/xml")
    }
    return f
}
String.prototype.startWith = function(a) {
    return this.indexOf(a) == 0
};
String.prototype.endWith = function(a) {
    return this.charAt(this.length - 1) == a
};
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*ROME)/g, "")
};
String.prototype.leftPad = function(g, f) {
    if (!isNaN(f)) {
        var b = "";
        for (var d = this.length; d < f; d++) {
            b = b.concat(g)
        }
        b = b.concat(this);
        return b
    }
    return null
};
String.prototype.rightPad = function(g, f) {
    if (!isNaN(f)) {
        var b = this;
        for (var d = this.length; d < f; d++) {
            b = b.concat(g)
        }
        return b
    }
    return null
};
Array.prototype.clone = function() {
    var a = this.length;
    var c = [];
    for (var b = 0; b < a; b++) {
        if (typeof(this[b]) == "undefined" || this[b] == null) {
            c[b] = this[b];
            continue
        }
        if (this[b].constructor == Array) {
            c[b] = this[b].clone()
        } else {
            c[b] = this[b]
        }
    }
    return c
};
Array.prototype.insert = function(b, d) {
    if (isNaN(b) || b < 0 || b > this.length) {
        this.push(d)
    } else {
        var a = this.slice(b);
        this[b] = d;
        for (var c = 0; c < a.length; c++) {
            this[b + 1 + c] = a[c]
        }
    }
    return this
};
Array.prototype.remove = function(b) {
    for (var a = 0; a < this.length; a++) {
        if (b == this[a]) {
            this.splice(a, 1)
        }
    }
};
Array.prototype.each = function(d) {
    var a = this.length;
    for (var c = 0; c < a; c++) {
        try {
            d(this[c], c)
        } catch(b) {
            alert("Array.prototype.each:" + b.message)
        }
    }
};
var Form = {};
Form.setValue = function(d, b) {
    b = ROMEF(b);
    for (var a = 0; a < b.elements.length; a++) {
        var g = ROME(b.elements[a]);
        if (g.ROMEA("ztype") == "select") {
            g = g.parentElement
        }
        if (g.type == "checkbox" || g.type == "radio") {
            if (g.name) {
                ROMENS(g.name, d.get(g.name));
                continue
            }
        }
        var f = g.id.toLowerCase();
        if (d.get(f)) {
            ROMES(g, d.get(f))
        }
    }
};
Form.getData = function(g) {
    g = ROMEF(g);
    if (!g) {
        alert("查找表单元素失败!" + g);
        return
    }
    var d = new DataCollection();
    var b = g.elements;
    for (var f = 0; f < b.length; f++) {
        var h = ROME(b[f]);
        var a = h.id;
        if (!h.type) {
            continue
        }
        if (h.type == "checkbox" || h.type == "radio") {
            if (h.name) {
                d.add(h.name, ROMENV(h.name));
                continue
            }
        }
        if (!a) {
            continue
        }
        if (h.ROMEA("ztype") == "select") {
            h = h.parentElement
        }
        d.add(h.id, ROMEV(h))
    }
    return d
};
var ROMEE = {};
ROMEE.ROMEA = function(a, b) {
    b = b || this;
    b = ROME(b);
    return b.getAttribute ? b.getAttribute(a) : null
};
ROMEE.ROMET = function(a, b) {
    b = b || this;
    b = window.ROME(b);
    return window.ROMET(a, b)
};
ROMEE.visible = function(a) {
    a = a || this;
    a = ROME(a);
    if (a.style.display == "none") {
        return false
    }
    return true
};
ROMEE.toggle = function(a) {
    a = a || this;
    a = ROME(a);
    ROMEE[ROMEE.visible(a) ? "hide": "show"](a)
};
ROMEE.toString = function(b, d, h) {
    h = h || this;
    var a = [];
    var g = 0;
    for (var j in h) {
        if (!d || g >= d) {
            var c = null;
            try {
                c = h[j]
            } catch(f) {}
            if (!b) {
                if (typeof(c) == "function") {
                    c = "function()"
                } else {
                    if (("" + c).length > 100) {
                        c = ("" + c).substring(0, 100) + "..."
                    }
                }
            }
            a.push(j + ":" + c)
        }
        g++
    }
    return a.join("\n")
};
ROMEE.hide = function(a) {
    a = a || this;
    a = ROME(a);
    a.style.display = "none"
};
ROMEE.focusEx = function(b) {
    b = b || this;
    b = ROME(b);
    try {
        b.focus()
    } catch(a) {}
};
ROMEE.show = function(a) {
    a = a || this;
    a = ROME(a);
    a.style.display = ""
};
ROMEE.getForm = function(a) {
    a = a || this;
    a = ROME(a);
    if (isIE) {
        a = a.parentElement
    } else {
        if (isGecko) {
            a = a.parentNode
        }
    }
    if (!a) {
        return null
    }
    if (a.tagName.toLowerCase() == "form") {
        return a
    } else {
        return ROMEE.GetForm(a)
    }
};
ROMEE.disable = function(d) {
    d = d || this;
    d = ROME(d);
    if (d.tagName.toLowerCase() == "form") {
        var c = d.elements;
        for (var b = 0; b < c.length; b++) {
            var a = c[b];
            d.blur();
            d.disabled = "true"
        }
    } else {
        d.disabled = "true"
    }
};
ROMEE.enable = function(d) {
    d = d || this;
    d = ROME(d);
    if (d.tagName.toLowerCase() == "form") {
        var c = d.elements;
        for (var b = 0; b < c.length; b++) {
            var a = c[b];
            d.disabled = ""
        }
    } else {
        d.disabled = ""
    }
};
ROMEE.scrollTo = function(b) {
    b = b || this;
    b = ROME(b);
    var a = b.x ? b.x: b.offsetLeft,
    c = b.y ? b.y: b.offsetTop;
    window.scrollTo(a, c)
};
ROMEE.getDimensions = function(g) {
    g = g || this;
    g = ROME(g);
    var j;
    if (g.tagName.toLowerCase() == "script") {
        j = {
            width: 0,
            height: 0
        }
    } else {
        if (ROMEE.visible(g)) {
            if (isIE && g.offsetWidth == 0 && g.offsetHeight == 0) {
                j = {
                    width: g.currentStyle.width.replace(/\D/g, ""),
                    height: g.currentStyle.height.replace(/\D/g, "")
                }
            } else {
                j = {
                    width: g.offsetWidth,
                    height: g.offsetHeight
                }
            }
        } else {
            var d = g.style;
            var f = d.visibility;
            var k = d.position;
            var b = d.display;
            d.visibility = "hidden";
            d.position = "absolute";
            d.display = "block";
            var a = g.clientWidth;
            var c = g.clientHeight;
            d.display = b;
            d.position = k;
            d.visibility = f;
            j = {
                width: a,
                height: c
            }
        }
    }
    return j
};
ROMEE.getPosition = function(m) {
    m = m || this;
    m = ROME(m);
    var k = m.ownerDocument;
    if (m.parentNode === null || m.style.display == "none") {
        return false
    }
    var l = null;
    var j = [];
    var g;
    if (m.getBoundingClientRect) {
        g = m.getBoundingClientRect();
        var c = Math.max(k.documentElement.scrollTop, k.body.scrollTop);
        var d = Math.max(k.documentElement.scrollLeft, k.body.scrollLeft);
        var b = g.left + d - k.documentElement.clientLeft;
        var a = g.top + c - k.documentElement.clientTop;
        if (isIE) {
            b--;
            a--
        }
        return {
            x: b,
            y: a
        }
    } else {
        if (k.getBoxObjectFor) {
            g = k.getBoxObjectFor(m);
            var h = (m.style.borderLeftWidth) ? parseInt(m.style.borderLeftWidth) : 0;
            var f = (m.style.borderTopWidth) ? parseInt(m.style.borderTopWidth) : 0;
            j = [g.x - h, g.y - f]
        }
    }
    if (m.parentNode) {
        l = m.parentNode
    } else {
        l = null
    }
    while (l && l.tagName != "BODY" && l.tagName != "HTML") {
        j[0] -= l.scrollLeft;
        j[1] -= l.scrollTop;
        if (l.parentNode) {
            l = l.parentNode
        } else {
            l = null
        }
    }
    return {
        x: j[0],
        y: j[1]
    }
};
ROMEE.getPositionEx = function(c) {
    c = c || this;
    c = ROME(c);
    var f = ROMEE.getPosition(c);
    var d = window;
    var a, b;
    while (d != d.parent) {
        if (d.frameElement) {
            pos2 = ROMEE.getPosition(d.frameElement);
            f.x += pos2.x;
            f.y += pos2.y
        }
        a = Math.max(d.document.body.scrollLeft, d.document.documentElement.scrollLeft);
        b = Math.max(d.document.body.scrollTop, d.document.documentElement.scrollTop);
        f.x -= a;
        f.y -= b;
        d = d.parent
    }
    return f
};
ROMEE.getParent = function(a, b) {
    b = b || this;
    b = ROME(b);
    while (b) {
        if (b.tagName.toLowerCase() == a.toLowerCase()) {
            return ROME(b)
        }
        b = b.parentElement
    }
    return null
};
ROMEE.getParentByAttr = function(a, c, b) {
    b = b || this;
    b = ROME(b);
    while (b) {
        if (b.getAttribute(a) == c) {
            return ROME(b)
        }
        b = b.parentElement
    }
    return null
};
ROMEE.getTopLevelWindow = function() {
    var a = window;
    while (a != a.parent) {
        a = a.parent
    }
    return a
};
ROMEE.hasClassName = function(a, b) {
    b = b || this;
    b = ROME(b);
    return (new RegExp(("(^|\\s)" + a + "(\\s|ROME)"), "i").test(b.className))
};
ROMEE.addClassName = function(b, d, c) {
    c = c || this;
    c = ROME(c);
    var a = c.className;
    a = a ? a: "";
    if (!new RegExp(("(^|\\s)" + b + "(\\s|ROME)"), "i").test(a)) {
        if (d) {
            c.className = b + ((a.length > 0) ? " ": "") + a
        } else {
            c.className = a + ((a.length > 0) ? " ": "") + b
        }
    }
    return c.className
};
ROMEE.removeClassName = function(b, c) {
    c = c || this;
    c = ROME(c);
    var a = new RegExp(("(^|\\s)" + b + "(?=\\s|ROME)"), "i");
    c.className = c.className.replace(a, "").replace(/^\s+|\s+ROME/g, "");
    return c.className
};
ROMEE.computePosition = function(c, m, b, l, k, q, g, j) {
    var o = j ? j.document: document;
    var d = isQuirks ? o.body.clientWidth: o.documentElement.clientWidth;
    var a = isQuirks ? o.body.clientHeight: o.documentElement.clientHeight;
    var n = Math.max(o.documentElement.scrollLeft, o.body.scrollLeft);
    var f = Math.max(o.documentElement.scrollTop, o.body.scrollTop);
    if (!k || k.toLowerCase() == "all") {
        if (l - f + g - a < 0) {
            if (b - n + q - d < 0) {
                return {
                    x: b,
                    y: l
                }
            } else {
                return {
                    x: b - q,
                    y: l
                }
            }
        }
        if (c - n + q - d < 0) {
            return {
                x: c,
                y: m - g
            }
        } else {
            return {
                x: c - q,
                y: m - g
            }
        }
    } else {
        if (k.toLowerCase() == "right") {
            if (l - f + g - a < 0) {
                if (b - n + q - d < 0) {
                    return {
                        x: b,
                        y: l
                    }
                }
            }
            return {
                x: c,
                y: m - g
            }
        } else {
            if (k.toLowerCase() == "left") {
                if (l - f + g - a < 0) {
                    if (b - n - q > 0) {
                        return {
                            x: b,
                            y: l
                        }
                    }
                }
                return {
                    x: c - q,
                    y: m - g
                }
            }
        }
    }
};
var Server = {};
Server.RequestMap = {};
Server.MainServletURL = "MainServlet.jsp";
Server.ContextPath = CONTEXTPATH;
Server.Pool = [];
Server.getXMLHttpRequest = function() {
    for (var b = 0; b < Server.Pool.length; b++) {
        if (Server.Pool[b][1] == "0") {
            Server.Pool[b][1] = "1";
            return Server.Pool[b][0]
        }
    }
    var c;
    if (window.XMLHttpRequest) {
        c = new XMLHttpRequest()
    } else {
        if (window.ActiveXObject) {
            for (var b = 5; b > 1; b--) {
                try {
                    if (b == 2) {
                        c = new ActiveXObject("Microsoft.XMLHTTP")
                    } else {
                        c = new ActiveXObject("Msxml2.XMLHTTP." + b + ".0")
                    }
                } catch(a) {}
            }
        }
    }
    Server.Pool.push([c, "1"]);
    return c
};
Server.loadURL = function(a, c) {
    var b = Server.getXMLHttpRequest();
    b.open("GET", Server.ContextPath + a, true);
    b.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    b.onreadystatechange = function() {
        if (b.readyState == 4 && b.status == 200) {
            try {
                if (c) {
                    c(b.responseText)
                }
            } finally {
                for (var d = 0; d < Server.Pool.length; d++) {
                    if (Server.Pool[d][0] == b) {
                        Server.Pool[d][1] = "0";
                        break
                    }
                }
                b = null;
                c = null
            }
        }
    };
    b.send(null)
};
Server.loadScript = function(a) {
    document.write('<script type="text/javascript" src="' + Server.ContextPath + a + '"><\/script>')
};
Server.loadCSS = function(a) {
    if (isGecko) {
        var b = document.createElement("LINK");
        b.rel = "stylesheet";
        b.type = "text/css";
        b.href = a;
        document.getElementsByTagName("HEAD")[0].appendChild(b)
    } else {
        document.createStyleSheet(a)
    }
};
Server.getOneValue = function(c, b, d) {
    if (b && b.prototype == DataCollection.prototype) {
        Server.sendRequest(c, b, d)
    } else {
        var a = new DataCollection();
        a.add("_Param0", b);
        Server.sendRequest(c, a, d)
    }
};
Server.sendRequest = function(b, d, g, h, a) {
    var f;
    if (h != null && Server.RequestMap[h]) {
        f = Server.RequestMap[h];
        f.abort()
    } else {
        f = Server.getXMLHttpRequest()
    }
    f.open("POST", Server.ContextPath + Server.MainServletURL, true);
    f.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var c = "Method=" + b + "&Data=";
    if (d) {
        c += encodeURL(htmlEncode(d.toXML()))
    }
    c += "&Url=" + encodeURL(window.location.pathname);
    Server._ResponseDC = null;
    f.onreadystatechange = function() {
        Server.onRequestComplete(f, g)
    };
    f.send(c)
};
Server.onRequestComplete = function(Request, func) {
    if (Request.readyState == 4 && Request.status == 200) {
        try {
            var xmlDoc = Request.responseXML;
            var dc = new DataCollection();
            if (xmlDoc) {
                if (dc.parseXML(xmlDoc)) {
                    dc.Status = dc.get("_ZVING_STATUS");
                    dc.Message = dc.get("_ZVING_MESSAGE");
                    if (dc.get("_ZVING_SCRIPT")) {
                        eval(dc.get("_ZVING_SCRIPT"))
                    }
                }
                Server._ResponseDC = dc;
                xmlDoc = null
            } else {
                dc.Status = 0;
                dc.Message = "服务器发生异常,未获取到数据!"
            }
            if (func) {
                func(dc)
            }
        } finally {
            for (var i = 0; i < Server.Pool.length; i++) {
                if (Server.Pool[i][0] == Request) {
                    Server.Pool[i][1] = "0";
                    break
                }
            }
            Request = null;
            func = null
        }
    }
};
Server.getResponse = function() {
    return Server._ResponseDC
};
var Page = {};
Page.clickFunctions = [];
Page.click = function(b) {
    for (var a = 0; a < Page.clickFunctions.length; a++) {
        Page.clickFunctions[a](b)
    }
    if (window != window.parent) {
        window.parent.Page.click()
    }
};
Page.onClick = function(a) {
    Page.clickFunctions.push(a)
};
Page._Sort = function(b, a) {
    var d = b[1];
    var c = a[1];
    if (typeof(d) == "number") {
        if (typeof(c) == "number") {
            if (d > c) {
                return 1
            } else {
                if (d == c) {
                    return 0
                } else {
                    return - 1
                }
            }
        }
        return - 1
    } else {
        if (typeof(c) == "number") {
            return 1
        } else {
            return 0
        }
    }
};
Page.loadFunctions = [];
Page.load = function() {
    if (window._OnLoad) {
        try {
            window._OnLoad()
        } catch(b) {}
    }
    Page.loadFunctions.sort(Page._Sort);
    for (var a = 0; a < Page.loadFunctions.length; a++) {
        try {
            Page.loadFunctions[a][0]()
        } catch(b) {}
    }
};
Page.onLoad = function(b, a) {
    Page.loadFunctions.push([b, a])
};
Page.mouseDownFunctions = [];
Page.mousedown = function(b) {
    for (var a = 0; a < Page.mouseDownFunctions.length; a++) {
        Page.mouseDownFunctions[a](b)
    }
};
Page.onMouseDown = function(a) {
    Page.mouseDownFunctions.push(a)
};
Page.mouseUpFunctions = [];
Page.mouseup = function(b) {
    for (var a = 0; a < Page.mouseUpFunctions.length; a++) {
        Page.mouseUpFunctions[a](b)
    }
};
Page.onMouseUp = function(a) {
    Page.mouseUpFunctions.push(a)
};
Page.mouseMoveFunctions = [];
Page.mousemove = function(b) {
    for (var a = 0; a < Page.mouseMoveFunctions.length; a++) {
        Page.mouseMoveFunctions[a](b)
    }
};
Page.onMouseMove = function(a) {
    Page.mouseMoveFunctions.push(a)
};
if (document.attachEvent) {
    document.attachEvent("onclick", Page.click);
    document.attachEvent("onmousedown", Page.mousedown);
    window.attachEvent("onload", Page.load);
    document.attachEvent("onmouseup", Page.mouseup);
    document.attachEvent("onmousemove", Page.mousemove)
} else {
    document.addEventListener("click", Page.click, false);
    document.addEventListener("mousedown", Page.mousedown, false);
    window.addEventListener("load", Page.load, false);
    document.addEventListener("mouseup", Page.mouseup, false);
    document.addEventListener("mousemove", Page.mousemove, false)
}
function DataTable() {
    this.Columns = null;
    this.Values = null;
    this.Rows = null;
    this.ColMap = {};
    DataTable.prototype.getRowCount = function() {
        return this.Rows.length
    };
    DataTable.prototype.getColCount = function() {
        return this.Columns.length
    };
    DataTable.prototype.getColName = function(a) {
        return this.Columns[a]
    };
    DataTable.prototype.get2 = function(b, a) {
        return this.Rows[b].get2(a)
    };
    DataTable.prototype.get = function(a, b) {
        return this.Rows[a].get(b)
    };
    DataTable.prototype.getDataRow = function(a) {
        return this.Rows[a]
    };
    DataTable.prototype.init = function(d, a) {
        this.Values = a;
        this.Columns = [];
        this.Rows = [];
        for (var c = 0; c < d.length; c++) {
            var b = {};
            b.Name = d[c][0].toLowerCase();
            b.Type = d[c][1];
            this.Columns[c] = b;
            this.ColMap[b.Name] = c
        }
        for (var c = 0; c < a.length; c++) {
            var f = new DataRow(this, c);
            this.Rows[c] = f
        }
    };
    DataTable.prototype.toString = function() {
        var a = [];
        a.push("<columns><![CDATA[[");
        for (var c = 0; c < this.Columns.length; c++) {
            if (c != 0) {
                a.push(",")
            }
            a.push("[");
            a.push('"' + this.Columns[c].Name + '",');
            a.push(this.Columns[c].Type);
            a.push("]")
        }
        a.push("]]]></columns>");
        a.push("<values><![CDATA[[");
        for (var c = 0; c < this.Values.length; c++) {
            if (c != 0) {
                a.push(",")
            }
            a.push("[");
            for (var b = 0; b < this.Columns.length; b++) {
                if (b != 0) {
                    a.push(",")
                }
                if (this.Values[c][b] == null || typeof(this.Values[c][b]) == "undefined") {
                    a.push('"_ZVING_NULL"')
                } else {
                    a.push('"' + this.Values[c][b] + '"')
                }
            }
            a.push("]")
        }
        a.push("]]]></values>");
        return a.join("")
    }
}
function DataRow(b, a) {
    this.DT = b;
    this.Index = a;
    DataRow.prototype.get2 = function(c) {
        return this.DT.Values[this.Index][c]
    };
    DataRow.prototype.getColCount = function() {
        return this.DT.Columns.length
    };
    DataTable.prototype.getColName = function(c) {
        return this.DT.Columns[c]
    };
    DataRow.prototype.get = function(d) {
        d = d.toLowerCase();
        var f = this.DT.ColMap[d];
        if (typeof(f) == "undefined") {
            return null
        }
        return this.DT.Values[this.Index][f]
    };
    DataRow.prototype.set = function(f, d) {
        f = f.toLowerCase();
        var g = this.DT.ColMap[f];
        if (typeof(g) == "undefined") {
            return
        }
        this.DT.Values[this.Index][g] = d
    };
    DataRow.prototype.set2 = function(c, d) {
        this.DT.Values[this.Index][c] = d
    }
}
function DataCollection() {
    this.map = {};
    this.valuetype = {};
    this.keys = [];
    DataCollection.prototype.get = function(ID) {
        if (typeof(ID) == "number") {
            return this.map[this.keys[ID]]
        }
        return this.map[ID]
    };
    DataCollection.prototype.getKey = function(index) {
        return this.keys[index]
    };
    DataCollection.prototype.size = function() {
        return this.keys.length
    };
    DataCollection.prototype.remove = function(ID) {
        if (typeof(ID) == "number") {
            this.map[this.keys[ID]] = null;
            this.keys[ID]
        }
        return this.map[ID]
    };
    DataCollection.prototype.toQueryString = function() {
        var arr = [];
        for (var i = 0; i < this.keys.length; i++) {
            if (this.map[this.keys[i]] == null || this.map[this.keys[i]] == "") {
                continue
            }
            if (i != 0) {
                arr.push("&")
            }
            arr.push(this.keys[i] + "=" + this.map[this.keys[i]])
        }
        return arr.join("")
    };
    DataCollection.prototype.parseXML = function(xmlDoc) {
        var coll = xmlDoc.documentElement;
        if (!coll) {
            return false
        }
        var nodes = coll.childNodes;
        var len = nodes.length;
        for (var i = 0; i < len; i++) {
            var node = nodes[i];
            var Type = node.getAttribute("Type");
            var ID = node.getAttribute("ID");
            this.valuetype[ID] = Type;
            if (Type == "String") {
                var v = node.firstChild.nodeValue;
                if (v == Constant.Null) {
                    v = null
                }
                this.map[ID] = v
            } else {
                if (Type == "StringArray") {
                    this.map[ID] = eval("[" + node.firstChild.nodeValue + "]")
                } else {
                    if (Type == "DataTable" || Type == "Schema" || Type == "SchemaSet") {
                        this.parseDataTable(node, "DataTable")
                    } else {
                        this.map[ID] = node.getAttribute("Value")
                    }
                }
            }
            this.keys.push(ID)
        }
        return true
    };
    DataCollection.prototype.parseDataTable = function(node, strType) {
        var cols = node.childNodes[0].childNodes[0].nodeValue;
        cols = "var _TMP1 = " + cols + "";
        eval(cols);
        cols = _TMP1;
        var values = node.childNodes[1].childNodes[0].nodeValue;
        values = "var _TMP2 = " + values + "";
        eval(values);
        values = _TMP2;
        var obj;
        obj = new DataTable();
        obj.init(cols, values);
        this.add(node.getAttribute("ID"), obj)
    };
    DataCollection.prototype.toXML = function() {
        var arr = [];
        arr.push('<?xml version="1.0" encoding="UTF-8"?>');
        arr.push("<collection>");
        for (var ID in this.map) {
            try {
                var v = this.map[ID];
                arr.push('<element ID="' + ID + '" Type="' + this.valuetype[ID] + '">');
                if (this.valuetype[ID] == "DataTable") {
                    arr.push(v.toString())
                } else {
                    if (this.valuetype[ID] == "String") {
                        if (v == null || typeof(v) == "undefined") {
                            arr.push("<![CDATA[" + Constant.Null + "]]>")
                        } else {
                            arr.push("<![CDATA[" + v + "]]>")
                        }
                    } else {
                        arr.push(v)
                    }
                }
                arr.push("</element>")
            } catch(ex) {
                alert("DataCollection.toXML():" + ID + "," + ex.message)
            }
        }
        arr.push("</collection>");
        return arr.join("")
    };
    DataCollection.prototype.add = function(ID, Value, Type) {
        this.map[ID] = Value;
        this.keys.push(ID);
        if (Type) {
            this.valuetype[ID] = Type
        } else {
            if (Value && Value.getDataRow) {
                this.valuetype[ID] = "DataTable"
            } else {
                this.valuetype[ID] = "String"
            }
        }
    }
}
var Cookie = {};
Cookie.Spliter = "_ZVING_SPLITER_";
Cookie.get = function(c) {
    var d = document.cookie.split("; ");
    for (i = 0; i < d.length; i++) {
        var a = d[i].split("=");
        var f = a[0].trim();
        var b = a[1] ? a[1].trim() : "";
        if (f == c) {
            return unescape(b)
        }
    }
    return null
};
Cookie.getAll = function() {
    var f = document.cookie.split("; ");
    var g = [];
    for (i = 0; i < f.length; i++) {
        var b = f[i].split("=");
        var k = b[0].trim();
        var c = b[1] ? b[1].trim() : "";
        if (k.indexOf(Cookie.Spliter) >= 0) {
            continue
        }
        if (c.indexOf("^" + Cookie.Spliter) == 0) {
            var a = c.substring(Cookie.Spliter.length + 1, c.indexOf("ROME"));
            var h = [c];
            for (var d = 1; d < a; d++) {
                h.push(Cookie.get(k + Cookie.Spliter + d))
            }
            c = h.join("");
            c = c.substring(c.indexOf("ROME") + 1)
        }
        g.push([k, unescape(c)])
    }
    return g
};
Cookie.set = function(b, l, c, m, f, a, k) {
    if (!k) {
        var l = escape(l)
    }
    if (!b || !l) {
        return false
    }
    if (!m) {
        m = "/"
    }
    if (c != null) {
        if (/^[0-9]+ROME/.test(c)) {
            c = new Date(new Date().getTime() + c * 1000).toGMTString()
        } else {
            var d = DateTime.parseDate(c);
            if (d) {
                c = d.toGMTString()
            } else {
                c = undefined
            }
        }
    }
    if (!k) {
        Cookie.remove(b, m, f)
    }
    var h = b + "=" + l + ";" + ((c) ? " expires=" + c + ";": "") + ((m) ? "path=" + m + ";": "") + ((f) ? "domain=" + f + ";": "") + ((a && a != 0) ? "secure": "");
    if (h.length < 4096) {
        document.cookie = h
    } else {
        var j = Math.ceil(l.length * 1 / 3800);
        for (var g = 0; g < j; g++) {
            if (g == 0) {
                Cookie.set(b, "^" + Cookie.Spliter + "|" + j + "ROME" + l.substr(0, 3800), c, m, f, a, true)
            } else {
                Cookie.set(b + Cookie.Spliter + g, l.substr(g * 3800, 3800), c, m, f, a, true)
            }
        }
    }
    return true
};
Cookie.remove = function(c, f, d) {
    var b = Cookie.get(c);
    if (!c || b == null) {
        return false
    }
    if (escape(b).length > 3800) {
        var a = Math.ceil(escape(b).length * 1 / 3800);
        for (i = 1; i < a; i++) {
            document.cookie = c + Cookie.Spliter + i + "=;" + ((f) ? "path=" + f + ";": "") + ((d) ? "domain=" + d + ";": "") + "expires=Thu, 01-Jan-1970 00:00:01 GMT;"
        }
    }
    document.cookie = c + "=;" + ((f) ? "path=" + f + ";": "") + ((d) ? "domain=" + d + ";": "") + "expires=Thu, 01-Jan-1970 00:00:01 GMT;";
    return true
};
var Misc = {};
Misc.setButtonText = function(a, b) {
    ROME(a).childNodes[1].innerHTML = b + "&nbsp;"
};
Misc.withinElement = function(c, d) {
    return false;
    var b = c.relatedTarget;
    while (b && b != d && b != document.body) {
        try {
            b = b.parentNode
        } catch(a) {
            alert("Misc.withinElement:" + a.message);
            return false
        }
    }
    return b == d
};
Misc.copyToClipboard = function(h) {
    if (h == null) {
        return
    }
    if (window.clipboardData) {
        window.clipboardData.setData("Text", h)
    } else {
        if (window.netscape) {
            try {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect")
            } catch(c) {
                Dialog.alert("Firefox自动复制功能未启用！<br>请<a href='about:config' target='_blank'>点击此处</a> 将’signed.applets.codebase_principal_support’设置为’true’")
            }
            var d = Components.classes["@mozilla.org/widget/clipboard;1"].createInstance(Components.interfaces.nsIClipboard);
            if (!d) {
                return
            }
            var b = Components.classes["@mozilla.org/widget/transferable;1"].createInstance(Components.interfaces.nsITransferable);
            if (!b) {
                return
            }
            b.addDataFlavor("text/unicode");
            var g = new Object();
            var a = new Object();
            var g = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
            var j = h;
            g.data = j;
            b.setTransferData("text/unicode", g, j.length * 2);
            var f = Components.interfaces.nsIClipboard;
            if (!d) {
                return
            }
            d.setData(b, null, f.kGlobalClipboard)
        } else {
            alert("该浏览器不支持自动复制功能！");
            return
        }
    }
    Dialog.alert("己复制文字：<br><br><font color='red'>" + h + "</font><br><br>到剪贴板", null, 400, 200)
};
Misc.lockSelect = function(a) {
    if (!a) {
        a = document.body
    }
    if (isGecko) {
        a.style.MozUserSelect = "none";
        a.style.MozUserInput = "none"
    } else {
        document.selection.empty();
        a.onselectstart = stopEvent
    }
};
Misc.unlockSelect = function(a) {
    if (!a) {
        a = document.body
    }
    if (isGecko) {
        a.style.MozUserSelect = "";
        a.style.MozUserInput = ""
    } else {
        a.onselectstart = null
    }
};
Misc.lockScroll = function(a) {
    if (!a) {
        a = window
    }
    if (isIE) {
        a.document.body.attachEvent("onmousewheel", a.stopEvent)
    } else {
        a.addEventListener("DOMMouseScroll", a.stopEvent, false)
    }
};
Misc.unlockScroll = function(a) {
    if (!a) {
        a = window
    }
    if (isIE) {
        a.document.body.detachEvent("onmousewheel", a.stopEvent);
        a.document.body.detachEvent("onmousewheel", a.stopEvent)
    } else {
        a.removeEventListener("DOMMouseScroll", a.stopEvent, false)
    }
};
Misc.debug = function(event) {
    if (event.altKey && event.shiftKey && event.ctrlKey && event.keyCode == 68) {
        alert(eval(prompt("请输入要执行的JavaScript语句:")))
    }
};
if (isIE) {
    document.attachEvent("onkeyup", Misc.debug)
} else {
    document.addEventListener("keyup", Misc.debug, false)
}
var Application = {};
Application.isHMenu = false;
Application.onMainMenuClick = function(k, g) {
    if (Application.LastClickMainMenu) {
        Application.LastClickMainMenu.className = ""
    }
    Application.LastClickMainMenu = k;
    k.className = "liOver";
    var j = ROME("_ChildMenu");
    var b = ROME("_Child" + k.id);
    if (!b) {
        var d = k.ChildArray;
        var h = [];
        for (var c = 0; c < d.length; c++) {
            var a = "_ChildMenuItem_" + d[c][0];
            h.push("<div id='" + a + "' class='divtab' onClick='Application.onChildMenuClick(this)' onMouseOver='Application.onChildMenuMouseOver(this)' onMouseOut='Application.onChildMenuMouseOut(this)' url='" + d[c][2] + "'><img src='" + d[c][3] + "' /><b><span>" + d[c][1] + "</span></b></div>")
        }
        b = document.createElement("div");
        b.id = "_Child" + k.id;
        b.innerHTML = h.join("");
        j.appendChild(b)
    }
    var f = j.childNodes;
    for (var c = 0; c < f.length; c++) {
        ROMEE.hide(f[c])
    }
    ROMEE.show(b);
    Tab.setDivtabWidth(b);
    if (!k.CurrentItem) {
        k.CurrentItem = "_ChildMenuItem_" + d[0][0]
    }
    Application.onChildMenuClick(ROME(k.CurrentItem), g)
};
Application.onMainMenuMouseOver = function(a) {
    a.className = "liOver"
};
Application.onMainMenuMouseOut = function(a) {
    if (a != Application.LastClickMainMenu) {
        a.className = ""
    }
};
var StartTime;
Application.onChildMenuClick = function(c, a) {
    StartTime = new Date().getTime();
    if (Application.LastClickMainMenu) {
        Application.LastClickMainMenu.CurrentItem = c.id
    }
    window.location.hash = c.parentElement.id.substr("_Child_Menu_".length) + "_" + c.id.substr("_ChildMenuItem_".length);
    if (!a) {
        var b = c.getAttribute("url");
        ROME("_MainArea").src = b
    }
    Tab.onTabClick(c)
};
Application.onChildMenuMouseOver = function(a) {
    Tab.onTabMouseOver(a)
};
Application.onChildMenuMouseOut = function(a) {
    Tab.onTabMouseOut(a)
};
Page.onLoad(function() {
    if (ROME("_Navigation")) {
        if (window.location.hash) {
            var a = window.location.hash.split("_");
            var c = ROME("_Menu_" + a[0].substr(1));
            c.CurrentItem = "_ChildMenuItem_" + a[1];
            Application.onMainMenuClick(c)
        } else {
            Application.onMainMenuClick(ROME("_Navigation").ROMET("li")[0])
        }
    } else {
        if (window.frameElement && window.frameElement.id == "_MainArea") {
            Application.CurrentSite = parent.ROMEV("_SiteSelector");
            Page.mousedown();
            if (!_DialogInstance && parent.Dialog._Array) {
                for (var b = 0; b < parent.Dialog._Array.length; b++) {
                    parent.ROME("_DialogDiv_" + parent.Dialog._Array[b]).outerHTML = "";
                    parent.ROME("_AlertBGDiv").hide();
                    parent.ROME("_DialogBGDiv").hide()
                }
            }
            parent.Application.setCurrentMenu(window.location.href)
        }
    }
    Application.layoutAdjust()
});
Application.setCurrentMenu = function(d) {
    if (d.indexOf("#") > 0) {
        d = d.substring(0, d.indexOf("#"))
    }
    if (ROME("_Navigation")) {
        var b = ROME("_Navigation").ROMET("li");
        for (var f = 0; f < b.length; f++) {
            var a = b[f].ChildArray;
            for (var c = 0; c < a.length; c++) {
                if (d.indexOf(a[c][2]) >= 0) {
                    Application.onMainMenuClick(b[f], true);
                    Application.onChildMenuClick(ROME("_ChildMenuItem_" + a[c][0]), true);
                    return
                }
            }
        }
    }
};
window.onresize = function() {
    Application.layoutAdjust()
};
Application.layoutAdjust = function() {
    if (ROME("_Navigation")) {
        if (document.body.clientWidth < 900) {
            if (ROME("_VMenutable").innerHTML.length > 40) {
                ROME("_HMenutable").innerHTML = ROME("_VMenutable").innerHTML;
                ROME("_VMenutable").innerHTML = "<div style='width:3px'></div>";
                Application.isHMenu = true
            }
            Tab.setDivtabWidth(ROME("_ChildMenu"))
        } else {
            if (ROME("_HMenutable").innerHTML.length > 0) {
                ROME("_VMenutable").innerHTML = ROME("_HMenutable").innerHTML;
                ROME("_HMenutable").innerHTML = ""
            }
            Application.isHMenu = false
        }
        Tab.initFrameHeight("_MainArea")
    }
};
Application.SiteChange = [];
Application.CurrentSite;
Application.onSiteChange = function(a) {
    Application.SiteChange.push(a)
};
Application.onChildSiteChange = function() {
    for (var a = 0; a < Application.SiteChange.length; a++) {
        Application.SiteChange[a]()
    }
};
Application.onParentSiteChange = function() {
    if (ROME("_Navigation")) {
        var a = new DataCollection();
        a.add("SiteID", ROMEV("_SiteSelector"));
        Cookie.set("SiteID", ROMEV("_SiteSelector"), "2100-01-01");
        Cookie.set("DocList.LastCatalog", "0", "2100-01-01");
        Cookie.set("Resource.LastImageLib", "0", "2100-01-01");
        Cookie.set("Resource.LastVideoLib", "0", "2100-01-01");
        Cookie.set("Resource.LastAudioLib", "0", "2100-01-01");
        Cookie.set("Resource.LastAttachLib", "0", "2100-01-01");
        Server.sendRequest("com.zving.platform.Application.changeSite", a,
        function() {
            ROME("_MainArea").contentWindow.Application.CurrentSite = ROMEV("_SiteSelector");
            ROME("_MainArea").contentWindow.Application.onChildSiteChange();
            getAllPriv()
        })
    }
};
function setAllPriv(f, a) {
    if (!a) {
        a = "0"
    }
    var c = ROMET("div");
    var d = ROMEE.getTopLevelWindow();
    if (d.Priv) {
        for (var b = 0; b < c.length; b++) {
            if (c[b].ROMEA("priv")) {
                d.Priv.setBtn(f, a, c[b].ROMEA("priv"), c[b])
            }
        }
    }
}
Application.logout = function() {
    Dialog.confirm("你确认要退出系统吗？",
    function() {
        Server.sendRequest("com.zving.platform.Application.logout")
    })
};
Application.changePassword = function() {
    var a = new Dialog("ChangePassword");
    a.Widht = 450;
    a.Height = 150;
    a.Title = "修改密码";
    a.URL = "Platform/ChangePasswordDialog.jsp";
    a.OKEvent = function() {
        if (ROMEDW.Verify.hasError()) {
            return
        }
        var b = ROMEDW.Form.getData("FChangePassword");
        Server.sendRequest("com.zving.platform.Application.changePassword", b,
        function(c) {
            if (c.Status == 1) {
                Dialog.alert(c.Message);
                ROMED.close()
            } else {
                Dialog.alert(c.Message)
            }
        })
    };
    a.onLoad = function() {
        ROMEDW.ROME("OldPassword").focus()
    };
    a.show()
};
var DragManager = {};
DragManager.DragProxy = null;
DragManager.onMouseOver = function(evt, ele) {
    if (DragManager.DragFlag) {
        var dragOver = ele.getAttribute("dragOver");
        if (dragOver) {
            var func = eval(dragOver);
            func.apply(ele, arguments)
        }
    }
};
DragManager.onMouseOut = function(evt, ele) {
    if (DragManager.DragFlag) {
        var dragOut = ele.getAttribute("dragOut");
        if (dragOut) {
            var func = eval(dragOut);
            func.apply(ele, arguments)
        }
    }
};
DragManager.onMouseDown = function(a, c) {
    var a = getEvent(a);
    var b = ROME(a.srcElement).getParentByAttr("drag", "false");
    if (b) {
        return
    }
    DragManager.DragSource = c;
    DragManager.StartFlag = true
};
DragManager.onMouseMove = function(evt) {
    evt = getEvent(evt);
    if (DragManager.StartFlag) {
        DragManager.DragFlag = true;
        DragManager.StartFlag = false;
        var dragStart = DragManager.DragSource.getAttribute("dragStart");
        if (dragStart) {
            var func = eval(dragStart);
            func.apply(DragManager.DragSource, arguments)
        }
    } else {
        if (DragManager.DragFlag) {
            if (DragManager.DragProxy) {
                var pos = getEventPosition(evt);
                DragManager.DragProxy.style.left = (pos.x - DragManager.DragProxy.cx) + "px";
                if (DragManager.DragProxy == DragManager.DragSource) {
                    DragManager.DragProxy.style.top = (pos.y - DragManager.DragProxy.cy) + "px"
                } else {
                    DragManager.DragProxy.style.top = (pos.y + 5) + "px"
                }
            }
        } else {
            if (DragManager.ChildDragFlag) {
                if (DragManager.DragProxy) {
                    var pos = getEventPosition(evt);
                    DragManager.DragProxy.style.left = (pos.x - DragManager.DragProxy.cx) + "px";
                    if (DragManager.DragProxy == DragManager.DragSource) {
                        DragManager.DragProxy.style.top = (pos.y - DragManager.DragProxy.cy) + "px"
                    } else {
                        DragManager.DragProxy.style.top = (pos.y + 5) + "px"
                    }
                }
            } else {
                var pw = ROMEE.getTopLevelWindow();
                if (pw.DragManager != null && pw.DragManager.DragFlag) {
                    pw.DragManager.onMouseMove(evt)
                }
            }
        }
    }
};
DragManager.onMouseUp = function(evt, ele) {
    if (DragManager.DragFlag) {
        DragManager.onMouseOut.apply(ele, arguments);
        var dragEnd = ele.getAttribute("dragEnd");
        var func = eval(dragEnd);
        func.apply(ele, arguments);
        if (DragManager.DragProxy && DragManager.DragSource != DragManager.DragProxy) {
            DragManager.DragProxy.style.display = "none"
        }
    }
    Misc.unlockSelect();
    DragManager.DragProxy = null;
    DragManager.DragSource = null;
    DragManager.DragFlag = false;
    DragManager.StartFlag = false
};
DragManager.onDragExit = function() {
    var pw = ROMEE.getTopLevelWindow();
    DragManager.DragFlag = false;
    pw.DragManager.DragFlag = false;
    if (!DragManager.DragSource) {
        return
    }
    var dragExit = DragManager.DragSource.getAttribute("dragExit");
    if (dragExit) {
        var func = eval(dragExit);
        func.apply(this, arguments)
    }
    var dragOut = DragManager.DragSource.getAttribute("dragOut");
    if (dragOut) {
        var func = eval(dragOut);
        func.apply(this, arguments)
    }
    if (DragManager.DragProxy && DragManager.DragSource != DragManager.DragProxy) {
        DragManager.DragProxy.style.display = "none"
    }
    Misc.unlockSelect();
    DragManager.DragProxy = null;
    DragManager.DragSource = null;
    DragManager.DragFlag = false;
    DragManager.StartFlag = false
};
DragManager.doDrag = function(a, c) {
    var g;
    var f = ROMEE.getPositionEx(DragManager.DragSource);
    var b = getEventPosition(a);
    if (!c) {
        g = DragManager.DragSource;
        g.style.position = "absolute"
    } else {
        var d = ROMEE.getTopLevelWindow();
        if (typeof(c) == "string") {
            c = c.replace(/on\w*?=([\'\"]).*?\1/gi, "");
            c = c.replace(/drag\w*?=([\'\"]).*?\1/gi, "");
            var h = ROME("_DragProxy");
            if (!h) {
                h = d.document.createElement("div");
                h.id = "_DragProxy";
                h.style.position = "absolute";
                h.style.zIndex = 999;
                Misc.lockSelect(h);
                d.document.body.appendChild(h)
            }
            h.innerHTML = c;
            h.style.display = "";
            h.style.left = (2 * f.x - b.x) + "px";
            h.style.top = (2 * f.y - b.y) + "px";
            g = h
        } else {
            g = c;
            g.style.position = "absolute"
        }
    }
    g.cx = b.x - f.x;
    g.cy = b.y - f.y;
    DragManager.DragProxy = g;
    Misc.lockSelect()
};
Page.onMouseUp(DragManager.onDragExit);
Page.onMouseMove(DragManager.onMouseMove);
var Effect = {};
Effect.NextID = 0;
Effect.initCtrlStyle = function(a) {
    a = ROME(a);
    var b = a.type;
    switch (b) {
    case "text":
    case "password":
    case "":
        a.addClassName("inputText");
        a.onmouseenter = function() {
            this.style.borderColor = "#00aaee"
        };
        a.onmouseleave = function() {
            this.style.borderColor = ""
        };
        a.onfocusFunc = a.onfocus;
        a.onfocus = function() {
            if (typeof(a.onfocusFunc) == "function") {
                try {
                    a.onfocusFunc()
                } catch(c) {}
            }
            this.style.borderColor = "#FF6D06";
            this.onmouseenter = null;
            this.onmouseleave = null
        };
        a.onblurFunc = a.onblur;
        a.onblur = function() {
            if (typeof(a.onblurFunc) == "function") {
                try {
                    a.onblurFunc()
                } catch(c) {}
            }
            this.style.borderColor = "";
            this.onmouseenter = function() {
                this.style.borderColor = "#00aaee"
            };
            this.onmouseleave = function() {
                this.style.borderColor = ""
            }
        };
        if (a.disabled == true) {
            a.addClassName("inputTextDisabled")
        }
        break;
    case "submit":
    case "reset":
    case "button":
        a.addClassName("btn");
        a.hideFocus = true;
        break;
    case "checkbox":
        a.addClassName("inputCheckbox");
        break;
    case "radio":
        a.addClassName("inputRadio");
        break;
    case "file":
        a.addClassName("inputFile");
        break;
    case "image":
        a.addClassName("inputImage");
        break;
    default:
    }
};
Effect.opacity = function(d, g, f, a, c) {
    g = g > 100 ? 100 : g;
    f = f > 100 ? 100 : f;
    g = g < 0 ? 0 : g;
    f = f < 0 ? 0 : f;
    var b = (f - g) * 100 / a;
    Effect._opacity(d, b, g, f, c)
};
Effect._opacity = function(d, c, g, f, b) {
    var a = true;
    g += c;
    if (c >= 0 && g >= f) {
        g = f;
        a = false
    }
    if (c < 0 && g <= f) {
        g = f;
        a = false
    }
    if (isIE) {
        d.style.filter = "alpha(opacity=" + g + ")"
    }
    if (isGecko) {
        d.style.opacity = g / 100
    }
    if (!a) {
        if (b) {
            b()
        }
    } else {
        Effect._opacityID = setTimeout(function() {
            Effect._opacity(d, c, g, f, b)
        },
        10)
    }
};
Effect.onBtnMouseover = function(a) {
    if (a.className == "divbtn") {
        a.addClassName("divbtnHover")
    }
};
Effect.onBtnMouseout = function(a) {
    if (a.hasClassName("divbtnHover")) {
        a.removeClassName("divbtnHover")
    }
};
ROMEE.hide = function(a) {
    if (!a) {
        a = this
    }
    a = ROME(a);
    if (a.tagName.toLowerCase() == "input" && a.type == "button") {
        if (a.parentElement && a.parentElement.getAttribute("ztype") == "ButtonWrapper") {
            a.parentElement.style.display = "none"
        }
    }
    a.style.display = "none"
};
ROMEE.show = function(a) {
    if (!a) {
        a = this
    }
    a = ROME(a);
    if (a.tagName.toLowerCase() == "input" && a.type == "button") {
        if (a.parentElement && a.parentElement.getAttribute("ztype") == "ButtonWrapper") {
            a.parentElement.style.display = ""
        }
    }
    a.style.display = ""
};
ROMEE.disable = function(d) {
    d = d || this;
    d = ROME(d);
    if (d.tagName.toLowerCase() == "form") {
        var c = d.elements;
        for (var b = 0; b < c.length; b++) {
            var a = c[b];
            d.blur();
            if (d.hasClassName("divbtn")) {
                d.addClassName("divbtnDisabled");
                if (d.onclick) {
                    d.onclickbak = d.onclick
                }
                d.onclick = null
            } else {
                d.disabled = "true"
            }
        }
    } else {
        if (d.ROMEA("ztype") == "select") {
            Selector.setDisabled(d, true)
        } else {
            if (d.hasClassName("divbtn")) {
                d.addClassName("divbtnDisabled");
                if (d.onclick) {
                    d.onclickbak = d.onclick
                }
                d.onclick = null
            } else {
                d.disabled = "true"
            }
        }
    }
};
ROMEE.enable = function(d) {
    d = d || this;
    d = ROME(d);
    if (d.tagName.toLowerCase() == "form") {
        var c = d.elements;
        for (var b = 0; b < c.length; b++) {
            var a = c[b];
            if (d.hasClassName("divbtnDisabled")) {
                d.className = "divbtn";
                if (d.onclickbak) {
                    d.onclick = d.onclickbak
                }
            } else {
                d.disabled = ""
            }
        }
    } else {
        if (d.ROMEA("ztype") == "select") {
            Selector.setDisabled(d, false)
        } else {
            if (d.hasClassName("divbtnDisabled")) {
                d.className = "divbtn";
                if (d.onclickbak) {
                    d.onclick = d.onclickbak
                }
            } else {
                d.disabled = ""
            }
        }
    }
};
Effect.initCtrl = function(b) {
    b = ROME(b);
    var a = b.ROMET("input").concat(b.ROMET("textarea"));
    a.each(Effect.initOneCtrl)
};
Effect.initOneCtrl = function(a) {
    Effect.initCtrlStyle(a);
    DateTime.initCtrl(a);
    Verify.initCtrl(a)
};
Page.onLoad(function() {
    Effect.initCtrl(document.body)
},
2);
Page.onLoad(function() {
    try {
        if (window.frameElement && isIE6 && !isQuirks) {
            var b = Math.max(document.documentElement.scrollHeight, document.body.scrollHeight);
            if (b > window.frameElement.offsetHeight) {
                ROMET("html")[0].style.overflowY = "scroll";
                ROMET("html")[0].style.overflowX = "hidden"
            }
        }
    } catch(a) {
        alert("脚本无法跨域操作！")
    }
},
10);
var Verify = {};
Verify.autoCloseOther = function(b, c) {
    if (!c) {
        b = getEvent(b);
        c = ROME(b.srcElement)
    }
    var a = c.ROMEA("ztype");
    if (a) {
        a = a.toLowerCase()
    }
    if (a != "select") {
        Selector.close()
    }
    if (a != "date") {
        Calendar.close()
    }
    if (a != "time") {
        TimeSelector.close()
    }
};
Verify.check = function(evt, ele) {
    if (!ele) {
        evt = getEvent(evt);
        ele = ROME(evt.srcElement)
    }
    var v = ele.ROMEA("verify");
    if (!v) {
        Verify.closeTip(ele);
        return
    }
    var condition = ele.ROMEA("condition");
    if (condition && !eval(condition)) {
        Verify.closeTip(ele);
        return
    }
    var msg = [];
    var sqlFlag = true;
    var Features = v.split("&&");
    var value = ROMEV(ele);
    if (ele.ROMEA("ztype") && ele.ROMEA("ztype").toLowerCase() == "select") {
        value = ROMEV(ele.parentElement)
    }
    if (value) {
        value = ("" + value).trim()
    }
    for (var i = 0; i < Features.length; i++) {
        var arr = Features[i].split("|");
        var name = "";
        var rule;
        if (arr.length == 2) {
            name = arr[0];
            rule = arr[1]
        } else {
            rule = Features[i]
        }
        var op = "=";
        if (rule.indexOf(">") > 0) {
            op = ">"
        } else {
            if (rule.indexOf("<") > 0) {
                op = "<"
            }
        }
        var f = rule.split(op);
        var fName = f[0];
        var fValue = null;
        if (f.length > 1) {
            fValue = f[1]
        }
        if (fName == "Any") {
            sqlFlag = false
        } else {
            if (fName == "Regex") {
                fValue = rule.substring(6);
                if (value == null || value == "" || !fValue) {
                    continue
                }
                var reg = fValue;
                if (!reg.startWith("^")) {
                    reg = "^" + reg
                }
                if (!reg.endWith("ROME")) {
                    reg += "ROME"
                }
                if (!new RegExp(reg).test(value)) {
                    msg.push(name)
                }
            } else {
                if (fName == "Script") {
                    fValue = rule.substring(7);
                    if (value == null || value == "" || !fValue) {
                        continue
                    }
                    if (!eval(fValue)) {
                        msg.push(name)
                    }
                } else {
                    if (fName == "NotNull") {
                        if (value == null || value == "") {
                            if (ele.ROMEA("ztype") && ele.ROMEA("ztype").toLowerCase() == "select") {
                                msg.push("必须选择" + name)
                            } else {
                                msg.push(name + "不能为空")
                            }
                        }
                    } else {
                        if (fName == "Number") {
                            if (value == null || value == "") {
                                continue
                            }
                            if (!isNumber(value)) {
                                msg.push(name + "必须是数字")
                            }
                        } else {
                            if (fName == "Time") {
                                if (value == null || value == "") {
                                    continue
                                }
                                var timearr = value.split(":");
                                if (timearr.length != 3) {
                                    msg.push(name + "的值" + value + "不是正确的时间!")
                                } else {
                                    if (!isInt(timearr[0]) || timearr[0] < 0 || timearr[0] > 23) {
                                        msg.push(name + "的值" + value + "错误，小时数" + timearr[0] + "不正确!");
                                        ele.focusEx()
                                    } else {
                                        if (!isInt(timearr[1]) || timearr[1] < 0 || timearr[1] > 59) {
                                            msg.push(name + "的值" + value + "错误，分钟数" + timearr[1] + "不正确!")
                                        }
                                    }
                                }
                            } else {
                                if (fName == "Int") {
                                    if (value == null || value == "") {
                                        continue
                                    }
                                    if (!isInt(value)) {
                                        msg.push(name + "必须是整数")
                                    }
                                } else {
                                    if (fName == "Date") {
                                        if (value == null || value == "") {
                                            continue
                                        }
                                        if (!/\d{4}\-\d{2}\-\d{2}/.test(value)) {
                                            msg.push(name + "必须是正确的日期")
                                        } else {
                                            var ts = value.split("-");
                                            var d = new Date(ts[0], --ts[1], ts[2]);
                                            if (d.getMonth() != ts[1] || d.getDate() != ts[2]) {
                                                msg.push(name + "必须是正确的日期")
                                            }
                                        }
                                    } else {
                                        if (fName == "Email") {
                                            if (value == null || value == "") {
                                                continue
                                            }
                                            var pattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*ROME/;
                                            if (value && value.match(pattern) == null) {
                                                msg.push(name + "不是正确的电子邮箱地址")
                                            }
                                        } else {
                                            if (fName == "Length") {
                                                if (value == null || value == "") {
                                                    continue
                                                }
                                                if (isNaN(fValue)) {
                                                    msg.push("校验规则错误，Length后面必须是数字")
                                                } else {
                                                    try {
                                                        var len = parseInt(fValue);
                                                        if (op == "=" && value.length != len) {
                                                            msg.push(name + "长度必须是" + len)
                                                        } else {
                                                            if (op == ">" && value.length <= len) {
                                                                msg.push(name + "长度必须大于" + len)
                                                            } else {
                                                                if (op == "<" && value.length >= len) {
                                                                    msg.push(name + "长度必须小于" + len)
                                                                }
                                                            }
                                                        }
                                                    } catch(ex) {
                                                        msg.push("校验规则错误，Length后面必须是整数" + ex.message)
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    if (msg.length > 0) {
        var txt = msg.join("<br>");
        if (txt != ele._VerifyMsg) {
            Verify.closeTip(ele);
            var tip;
            var afterEle = ele.ROMEA("element");
            if (afterEle) {
                tip = Tip.show(ROME(afterEle), txt)
            } else {
                tip = Tip.show(ele, txt)
            }
            ele._VerifyTip = tip;
            ele._VerifyMsg = txt
        }
    } else {
        Verify.closeTip(ele)
    }
};
Verify.closeTip = function(b, a) {
    if (!b) {
        a = getEvent(a);
        b = ROME(a.srcElement)
    }
    if (b.type == "blur") {
        b = ROME(b.srcElement)
    }
    if (b._VerifyTip) {
        b._VerifyTip.close();
        b._VerifyTip = null;
        b._VerifyMsg = null
    }
};
Verify.hasError = function(h, m) {
    var g;
    if (m) {
        m = ROME(m);
        g = m.ROMET("input").concat(m.ROMET("textarea"))
    } else {
        g = ROMET("input").concat(ROMET("textarea"))
    }
    var b = false;
    for (var f = 0; f < g.length; f++) {
        var l = ROME(g[f]);
        var a = l.id;
        if (l.ROMEA("ztype") == "select") {
            a = l.parentElement.id
        }
        var k = false;
        if (h) {
            for (var d = 0; d < h.length; d++) {
                if (a == ROME(h[d]).id) {
                    k = true
                }
            }
        }
        if (k) {
            Verify.closeTip(l);
            continue
        }
        Verify.check(null, l);
        if (!b && l._VerifyTip) {
            b = l
        }
    }
    if (b) {
        Dialog.alert("还有未正确填写的项，请参照提示修改!",
        function() {
            ROME(b).focusEx()
        });
        return true
    }
    return false
};
Verify.initCtrl = function(c) {
    c = ROME(c);
    c.attachEvent("onfocus", Verify.autoCloseOther);
    var b = c.ROMEA("verify");
    if (b) {
        c.attachEvent("onfocus", Verify.check);
        c.attachEvent("onkeyup", Verify.check);
        c.attachEvent("onchange", Verify.check);
        c.attachEvent("onblur", Verify.closeTip);
        var f = c.ROMEA("condition");
        if (b.indexOf("NotNull") >= 0 && !f) {
            var a = c.ROMEA("ztype");
            if (a) {
                a = a.toLowerCase()
            }
            if (a == "select") {
                c = c.getParent("div")
            }
            if (a == "date" || a == "time") {
                c = c.nextSibling
            }
            if (!c.nextSibling || !c.nextSibling.getAttribute || c.nextSibling.getAttribute("ztype") != "Verify") {
                var d = "";
                if (!ROMEE.visible(c)) {
                    d = "display:none"
                }
                c.insertAdjacentHTML("afterEnd", "<span style='color:red;padding-left:2px;padding-top:13px;" + d + "' ztype='Verify'>*</span>")
            }
        }
    }
};
window.setInterval(function() {
    var a = ROMET("input").concat(ROMET("textarea"));
    a.each(function(b) {
        b = ROME(b);
        if (b._VerifyTip) {
            Verify.check(null, b)
        }
    })
},
500);
var DataGrid = {};
Constant.SortString = "_ZVING_SORTSTRING";
Constant.PageIndex = "_ZVING_PAGEINDEX";
Constant.PageTotal = "_ZVING_PAGETOTAL";
Constant.TagBody = "_ZVING_TAGBODY";
Constant.ID = "_ZVING_ID";
Constant.Method = "_ZVING_METHOD";
Constant.Page = "_ZVING_PAGE";
Constant.Size = "_ZVING_SIZE";
Constant.Null = "_ZVING_NULL";
Constant.InsertRow = "_ZVING_INSERTROW";
Constant.DataTable = "_ZVING_DataTable";
DataGrid.onAllCheckClick = function(b) {
    b = ROME(b);
    var a = b.id;
    if (ROME(a + "_AllCheck").checked) {
        DataGrid.selectAll(b)
    } else {
        DataGrid.unselectAll(b)
    }
};
DataGrid.selectAll = function(d) {
    d = ROME(d);
    var c = d.id;
    var a = ROMEN(c + "_RowCheck");
    for (var b = 0; a && b < a.length; b++) {
        if (!a[b].disabled) {
            a[b].checked = true;
            DataGrid.onSelectorClick(a[b])
        }
    }
};
DataGrid.unselectAll = function(d) {
    d = ROME(d);
    var c = d.id;
    var a = ROMEN(c + "_RowCheck");
    for (var b = 0; a && b < a.length; b++) {
        if (!a[b].disabled) {
            a[b].checked = false;
            DataGrid.onSelectorClick(a[b])
        }
    }
};
DataGrid.getSelectedValue = function(b) {
    b = ROME(b);
    var a = b.id;
    return ROMENV(a + "_RowCheck")
};
DataGrid.getSelectedTreeValue = function(a) {
    a = ROME(a);
    return ROMENV(a.id + "_TreeRowCheck")
};
DataGrid.getSelectedRows = function(c) {
    c = ROME(c);
    var a = [];
    for (var b = 1; b < c.rows.length; b++) {
        if (c.rows[b].Selected) {
            a.push(c.rows[b])
        }
    }
    return a
};
DataGrid.getSelectedData = function(f) {
    f = ROME(f);
    var d = f.DataSource;
    var a = [];
    for (var b = 1; b < f.rows.length; b++) {
        if (f.rows[b].Selected) {
            a.push(d.Values[b - 1])
        }
    }
    var c = new DataTable();
    var g = [];
    for (var b = 0; b < d.Columns.length; b++) {
        g.push([d.Columns[b].Name, d.Columns[b].Type])
    }
    c.init(g, a);
    return c
};
DataGrid.SelectedBgColor = "#D8F79D";
DataGrid.MouseOverBgColor = "#EDFBD2";
DataGrid.onSelectorClick = function(c, a) {
    var b = ROME(c).getParent("tr");
    var f = b.parentNode.parentNode;
    if (c.tagName.toLowerCase() == "input") {
        b.Selected = c.checked
    } else {
        b.Selected = !b.Selected;
        ROME(f.id + "_RowCheck" + b.rowIndex).checked = b.Selected
    }
    DataGrid.onRowSelected(b, a);
    var d = f.ROMEA("multiSelect") != "false";
    if (a && d) {
        b.SelectorFlag = true
    }
};
DataGrid.onRowSelected = function(b, a) {
    if (typeof(b.DefaultBgColor) == "undefined") {
        b.DefaultBgColor = b.style.backgroundColor
    }
    if (b.Selected) {
        b.style.backgroundColor = DataGrid.SelectedBgColor
    } else {
        b.style.backgroundColor = b.DefaultBgColor
    }
};
DataGrid.onRowClick = function(d, a) {
    a = getEvent(a);
    var g = d.parentNode.parentNode;
    var f = g.ROMEA("multiSelect") != "false";
    for (var c = 1; c < g.rows.length; c++) {
        var h = g.rows[c];
        if (a) {
            if (!a.ctrlKey || !f) {
                if (h != d && h.Selected) {
                    if (d.SelectorFlag) {
                        continue
                    }
                    h.Selected = false;
                    var b = ROME(g.id + "_RowCheck" + h.rowIndex);
                    if (b) {
                        b.checked = false
                    }
                    DataGrid.onRowSelected(h)
                }
            }
        }
    }
    if (a && !d.SelectorFlag) {
        if (a.ctrlKey) {
            if (d.Selected) {
                d.Selected = false;
                var b = ROME(g.id + "_RowCheck" + h.rowIndex);
                if (b) {
                    b.checked = false
                }
                DataGrid.onRowSelected(d);
                d.SelectorFlag = false;
                return
            }
        }
    }
    var b = ROME(g.id + "_RowCheck" + d.rowIndex);
    if (b) {
        if (a && !d.SelectorFlag) {
            b.checked = true
        }
        d.Selected = b.checked
    } else {
        d.Selected = true
    }
    DataGrid.onRowSelected(d, a);
    d.SelectorFlag = false
};
DataGrid.onSort = function(h) {
    var j = h.getAttribute("direction");
    if (!j) {
        j = ""
    }
    if (j.toUpperCase() == "ASC") {
        j = ""
    } else {
        if (j == "") {
            j = " DESC"
        } else {
            if (j.toUpperCase() == "DESC") {
                j = " ASC"
            }
        }
    }
    var b = h.getAttribute("sortField");
    var f = h.parentNode.parentNode.parentNode;
    var g = f.getAttribute("SortString");
    var d = [];
    if (!g) {
        g = ""
    }
    var a = g.split(",");
    if (j) {
        d.push(b + j)
    }
    for (var c = 0; c < a.length; c++) {
        if (!a[c] || a[c].toLowerCase() == b.toLowerCase() || a[c].indexOf(b + " ") >= 0) {
            continue
        }
        d.push(a[c])
    }
    g = d.join();
    if (!g) {
        g = Constant.Null
    }
    DataGrid.setParam(f, Constant.SortString, g);
    DataGrid.loadData(f)
};
DataGrid.init = function(g) {
    g = ROME(g);
    DataGrid.setParam(g, Constant.ID, g.id);
    DataGrid.setParam(g, Constant.Method, g.getAttribute("method"));
    DataGrid.setParam(g, Constant.Page, g.getAttribute("page"));
    DataGrid.setParam(g, Constant.Size, g.getAttribute("size"));
    DataGrid.setParam(g, Constant.TagBody, g.TagBody);
    var d = parseInt(DataGrid.getParam(g, Constant.Size));
    var f = g.getAttribute("page");
    var c = g.ROMEA("autoFill") != "false";
    if (c) {
        if (f == "true") {
            if (g.rows.length - 2 < d && g.rows.length > 3) {
                var h = g.rows[g.rows.length - 2];
                h.cells[0].style.height = ROMEE.getDimensions(g.rows[1]).height * (d - g.rows.length + 3) + "px"
            }
        } else {
            d = 15;
            if (g.rows.length < 16 && g.rows.length > 2) {
                var h = g.rows[g.rows.length - 1];
                h.cells[0].style.height = ROMEE.getDimensions(g.rows[0]).height * (d + 2 - g.rows.length)
            }
        }
    }
    if (isGecko) {
        for (var b = 0; b < g.rows.length; b++) {
            for (var a = 0; a < g.rows[b].cells.length; a++) {
                g.rows[b].cells[a].style.MozUserSelect = "none"
            }
        }
    }
};
DataGrid.getParam = function(b, a) {
    b = ROME(b);
    return b.Params.get(a)
};
DataGrid.setParam = function(c, a, b) {
    c = ROME(c);
    if (!c.Params) {
        c.Params = new DataCollection()
    }
    c.Params.add(a, b)
};
DataGrid.toExcel = function(j, b) {
    j = ROME(j);
    var g = "_Excel_";
    var k = window.document.body;
    var h = ROME(g + "_Form");
    if (!h) {
        h = document.createElement("form")
    }
    h.id = g + "_Form";
    h.method = "post";
    h.action = Server.ContextPath + "Framework/Controls/DataGridToExcel.jsp";
    k.appendChild(h);
    var a = j.Params.keys;
    for (var d = 0; d < a.length; d++) {
        var c = ROME(g + a[d]);
        if (!c) {
            c = document.createElement("input")
        }
        c.type = "hidden";
        c.id = g + a[d];
        c.name = g + a[d];
        c.value = DataGrid.getParam(j, a[d]);
        h.appendChild(c)
    }
    var c = ROME(g + "_ZVING_ToExcelPageFlag");
    if (!c) {
        c = document.createElement("input")
    }
    c.type = "hidden";
    c.id = g + "_ZVING_ToExcelPageFlag";
    c.name = g + "_ZVING_ToExcelPageFlag";
    c.value = b ? "1": "0";
    h.appendChild(c);
    c = ROME(g + "_ZVING_ToExcelWidth");
    if (!c) {
        c = document.createElement("input")
    }
    c.type = "hidden";
    c.id = g + "_ZVING_ToExcelWidth";
    c.name = g + "_ZVING_ToExcelWidth";
    c.value = j.offsetWidth;
    h.appendChild(c);
    h.submit()
};
DataGrid.showLoading = function(c) {
    c = ROME(c);
    var a = ROME("_LoadingBGDiv");
    var b = ROME("_LoadingIconDiv");
    if (!a) {
        a = document.createElement("div");
        a.id = "_LoadingBGDiv";
        ROMEE.hide(a);
        a.style.cssText = "background-color:#ffc;position:absolute;z-index:800;opacity:0;filter:alpha(opacity=0);";
        document.body.appendChild(a);
        b = document.createElement("div");
        b.id = "_LoadingIconDiv";
        ROMEE.hide(b);
        b.innerHTML = "　<img src='" + Server.ContextPath + "Framework/Images/loadingGreen15px.gif'><font color=green> 正在加载......　</font>";
        b.style.cssText = "padding-top:5px;background-color:#ffc;position:absolute;z-index:801;height:20px;width:120px";
        document.body.appendChild(b)
    }
    var f = c.getPosition();
    var d = c.getDimensions();
    a.style.top = f.y + "px";
    a.style.left = f.x + "px";
    a.style.width = d.width + "px";
    a.style.height = d.height + "px";
    if (isGecko) {
        b.style.top = (f.y) + "px";
        b.style.left = (f.x) + "px"
    } else {
        b.style.top = (f.y + 2) + "px";
        b.style.left = (f.x + 2) + "px"
    }
    ROMEE.show(a);
    ROMEE.show(b)
};
DataGrid.closeLoading = function() {
    var a = ROME("_LoadingBGDiv");
    if (a) {
        ROMEE.hide(a);
        ROME("_LoadingIconDiv").hide()
    }
};
DataGrid.loadData = function(ele, func) {
    if (DataGrid.isLoading) {
        return
    }
    DataGrid.isLoading = true;
    try {
        ele = ROME(ele);
        var id = ele.id;
        if (!DataGrid.getParam(ele, Constant.TagBody)) {
            return
        }
        DataGrid.showLoading(ele);
        Server.sendRequest("com.zving.framework.controls.DataGridPage.doWork", ele.Params,
        function(response) {
            var dg = ele.getParentByAttr("ztype", "_DataGrid");
            if (dg) {
                ele = dg
            }
            if (!ele.parentNode) {
                return
            }
            if (!response.get("HTML") || response.get("HTML").length < 10) {
                return
            }
            var newEle = document.createElement("div");
            newEle.setAttribute("ztype", "_DataGrid");
            newEle.innerHTML = response.get("HTML");
            var table = ele.tagName.toLowerCase() == "table" ? ele: ele.childNodes[0];
            var afterEdit = table.afterEdit;
            var cancelEdit = table.cancelEdit;
            var beforeEdit = table.beforeEdit;
            ele.parentNode.replaceChild(newEle, ele);
            ele = null;
            if (isIE) {
                execScript(ROME(newEle).ROMET("script")[0].text)
            }
            eval("DataGrid_" + id + "_Init();");
            table = ROME(newEle.childNodes[0]);
            table.afterEdit = afterEdit;
            table.cancelEdit = cancelEdit;
            table.beforeEdit = beforeEdit;
            Effect.initCtrl(table);
            table.ROMET("div").each(function(div) {
                var ztype = ROME(div).ROMEA("ztype");
                if (ztype && ztype.toLowerCase() == "select") {
                    Selector.initCtrl(div)
                }
            });
            if (func) {
                func()
            }
            setTimeout(DataGrid.closeLoading, 200);
            table = null;
            newEle = null;
            ele = null;
            func = null
        })
    } finally {
        DataGrid.isLoading = false
    }
};
DataGrid.clear = function(h) {
    h = ROME(h);
    var f = parseInt(DataGrid.getParam(h, Constant.Size));
    var g = DataGrid.getParam(h, Constant.Page);
    if (!g) {
        f = 15
    }
    if (!h.RowHeight) {
        if (g && h.rows.length <= 3) {
            h.RowHeight = ROMEE.getDimensions(h.rows[0]).height
        } else {
            if (!g && h.rows.length == 1) {
                h.RowHeight = ROMEE.getDimensions(h.rows[0]).height
            } else {
                h.RowHeight = ROMEE.getDimensions(h.rows[1]).height
            }
        }
    }
    for (var d = h.rows.length - 2; d > 0; d--) {
        h.deleteRow(d)
    }
    var b = h.rows[0].cells.length;
    var k = h.insertRow(h.rows.length - 1);
    for (var c = 0; c < b; c++) {
        var a = k.insertCell( - 1);
        a.innerHTML = "&nbsp;"
    }
    k.cells[0].style.height = h.RowHeight * (f) + "px"
};
DataGrid.firstPage = function(a) {
    a = ROME(a);
    DataGrid.setParam(a, Constant.PageIndex, 0);
    DataGrid.loadData(a)
};
DataGrid.lastPage = function(d) {
    d = ROME(d);
    var c = DataGrid.getParam(d, Constant.PageTotal);
    var b = DataGrid.getParam(d, Constant.Size);
    var a = Math.ceil(parseInt(c) * 1 / parseInt(b));
    DataGrid.setParam(d, Constant.PageIndex, a - 1);
    DataGrid.loadData(d)
};
DataGrid.previousPage = function(b) {
    b = ROME(b);
    var a = DataGrid.getParam(b, Constant.PageIndex);
    DataGrid.setParam(b, Constant.PageIndex, parseInt(a) - 1);
    DataGrid.loadData(b)
};
DataGrid.nextPage = function(b) {
    b = ROME(b);
    var a = DataGrid.getParam(b, Constant.PageIndex);
    DataGrid.setParam(b, Constant.PageIndex, parseInt(a) + 1);
    DataGrid.loadData(b)
};
DataGrid._onContextMenu = function(b, a) {
    if (!b.Selected) {
        DataGrid.onRowClick(b, a)
    }
    a = getEvent(a);
    if (DataGrid.onContextMenu) {
        DataGrid.onContextMenu(b, a)
    } else {
        a = getEvent(a);
        var c = b.parentNode.parentNode;
        var g = c.id;
        var f = new Menu();
        f.Width = 150;
        f.setEvent(a);
        var d = a.srcElement.innerText;
        f.addItem("复制文本",
        function() {
            Misc.copyToClipboard(d)
        },
        "Icons/icon003a2.gif");
        f.addItem("导出本页成Excel",
        function() {
            DataGrid.toExcel(g)
        },
        "Icons/icon003a4.gif");
        f.addItem("导出全部成Excel",
        function() {
            DataGrid.toExcel(g, true)
        },
        "Icons/icon003a3.gif");
        f.show()
    }
    stopEvent(a)
};
DataGrid.treeClick = function(n) {
    var c = n.parentNode;
    var l, m;
    try {
        while (c) {
            var d = c.tagName.toLowerCase();
            if (d == "tr") {
                l = c
            }
            if (d == "table") {
                m = c;
                break
            }
            c = c.parentNode
        }
    } catch(k) {
        alert(k.message)
    }
    var f = false;
    if (n.src.indexOf("butExpand") > 0) {
        n.src = "" + Server.ContextPath + "Framework/Images/butCollapse.gif";
        f = true
    } else {
        n.src = "" + Server.ContextPath + "Framework/Images/butExpand.gif"
    }
    var o = m.rows;
    var b = parseInt(l.getAttribute("level"));
    for (var h = 0; h < o.length; h++) {
        if (o[h] === l) {
            for (var g = h + 1; g < o.length; g++) {
                var a = ROME(o[g]);
                if (parseInt(a.ROMEA("level")) > b) {
                    if (f) {
                        if (ROMEE.visible(a)) {
                            a.setAttribute("_TreeHideLevel", b);
                            ROMEE.hide(a)
                        }
                    } else {
                        if (a.ROMEA("_TreeHideLevel") == b) {
                            ROMEE.show(a);
                            a.setAttribute("_TreeHideLevel", null)
                        }
                    }
                } else {
                    break
                }
            }
            break
        }
    }
};
DataGrid.getSelectors = function(a) {
    a = ROME(a);
    return ROMEN(a.id + "_RowCheck")
};
DataGrid.deleteRow = function(h, c) {
    h = ROME(h);
    var g = h.rows[0];
    var b;
    for (var d = 0; d < g.cells.length; d++) {
        var a = g.cells[d].getAttribute("ztype");
        if (a && a.toLowerCase() == "rowno") {
            b = d;
            break
        }
    }
    if (b != null) {
        var f = parseInt(h.rows[c + 1].cells[b].innerText);
        for (var d = c + 2; d < h.DataSource.Rows.length + 1; d++) {
            h.rows[d].cells[b].innerText = "" + f++
        }
    }
    h.deleteRow(c + 1);
    h.DataSource.Values.splice(c, 1);
    h.DataSource.Rows.splice(c, 1)
};
DataGrid.insertRow = function(c, a, f, b) {
    c = ROME(c);
    if (!a) {
        a = c.DataSource.Rows.length
    }
    DataGrid.setParam(c, Constant.InsertRow, a);
    DataGrid.setParam(c, Constant.DataTable, c.DataSource);
    var d = c.id;
    DataGrid.loadData(d,
    function() {
        if (f) {
            DataGrid.changeStatus(ROME(d).rows[a + 1])
        }
        if (b) {
            b()
        }
    })
};
DataGrid.changeStatus = function(x, n) {
    x = ROME(x);
    var K = x.parentNode.parentNode;
    var z = K.DataSource;
    var y = x.rowIndex - 1;
    if (DataGrid.EditingRow && x != DataGrid.EditingRow) {
        DataGrid.changeStatus(DataGrid.EditingRow)
    }
    var C, J, B;
    try {
        for (var F = 0; F < K.rows.length; F++) {
            var s = K.rows[F];
            if (s.getAttribute("ztype") == "edit") {
                C = s
            }
            if (s.getAttribute("ztype") == "template") {
                J = s
            }
            if (s.getAttribute("ztype") == "head") {
                B = s
            }
        }
        if (!B) {
            B = table.rows[0]
        }
    } catch(I) {
        alert(I.message)
    }
    var A = new DataRow(z, y);
    if (!K.OldValues) {
        K.OldValues = []
    }
    if (!x.EditStatus) {
        x.EditStatus = true;
        DataGrid.EditingRow = x
    } else {
        x.EditStatus = false;
        if (!K.OldValues[y]) {
            var g = z.Values[y].clone();
            K.OldValues[y] = g
        }
        if (n != "Cancel" && K.afterEdit) {
            if (!K.afterEdit(x, A)) {
                return false
            }
        }
        DataGrid.EditingRow = null
    }
    var h = K.OldValues[y];
    for (var F = 0; F < B.cells.length; F++) {
        var d = B.cells[F];
        var w = d.getAttribute("ztype");
        var f = d.getAttribute("field");
        var r = false;
        if (w && w.toLowerCase() == "selector") {
            ROME(K.id + "_RowCheck" + x.rowIndex).disabled = x.EditStatus ? true: false
        } else {
            if (w && w.toLowerCase() == "rowno") {
                x.cells[F].innerHTML = x.cells[F].getAttribute("rowno")
            } else {
                if (w && w.toLowerCase() == "checkbox") {
                    var H = x.cells[F].getAttribute("checkedvalue");
                    if (H == null) {
                        H = "Y"
                    }
                    if (h) {
                        r = h[z.ColMap[f.toLowerCase()]] != A.get(f)
                    }
                    var t = H == A.get(f) ? "checked": "";
                    ROME(K.id + "_" + f + "_Checkbox" + x.rowIndex).disabled = x.EditStatus ? false: true
                } else {
                    if (w && w.toLowerCase() == "dropdownlist") {
                        if (h) {
                            r = h[z.ColMap[f.toLowerCase()]] != A.get(f)
                        }
                        ROME(K.id + "_" + f + "_DropDownList" + x.rowIndex).disabled = x.EditStatus ? false: true
                    } else {
                        var b = [];
                        if (w && w.toLowerCase() == "tree") {
                            var a = parseInt(x.getAttribute("level"));
                            var l = 0;
                            var c = true;
                            if (x.rowIndex != K.rows.length - 1) {
                                l = parseInt(K.rows[x.rowIndex + 1].getAttribute("level"));
                                c = K.rows[x.rowIndex + 1].style.display != "none"
                            }
                            for (var D = 0; D < a; D++) {
                                b.push("&nbsp;&nbsp;")
                            }
                            if (a < l) {
                                if (c) {
                                    b.push("<img src='" + Server.ContextPath + "Framework/Images/butExpand.gif' onclick='DataGrid.treeClick(this)'/>&nbsp;")
                                } else {
                                    b.push("<img src='" + Server.ContextPath + "Framework/Images/butCollapse.gif' onclick='DataGrid.treeClick(this)'/>&nbsp;")
                                }
                            } else {
                                b.push("<img src='" + Server.ContextPath + "Framework/Images/butNoChild.gif'/>&nbsp;")
                            }
                        }
                        var v = unescape(x.EditStatus ? K.EditArray[F] : K.TemplateArray[F]);
                        var q = /\ROME\{(\w+?)\}/gi;
                        var u = 0;
                        b.push(v.replace(q,
                        function(k, j) {
                            if (h) {
                                if (h[z.ColMap[j.toLowerCase()]] != A.get(j)) {
                                    r = true
                                }
                            }
                            var L = A.get(j);
                            if ((L === "" || L === null) && !x.EditStatus) {
                                L = "&nbsp;"
                            }
                            return L
                        }));
                        x.cells[F].innerHTML = b.join("")
                    }
                }
            }
        }
        if (r) {
            x.cells[F].style.backgroundColor = "#FEB34E"
        } else {
            x.cells[F].style.backgroundColor = ""
        }
        if (!x.ModifyFlag && r) {
            x.ModifyFlag = true
        }
    }
    var b = x.ROMET("div");
    var G = b.length;
    for (var F = G; F > 0; F--) {
        var o = ROME(b[F - 1]);
        var n = o.ROMEA("ztype");
        if (n && n.toLowerCase() == "select") {
            Selector.initCtrl(o)
        }
    }
    if (isIE) {
        x.onselectstart = x.EditStatus ? null: stopEvent
    } else {
        for (var E = 0; E < x.cells.length; E++) {
            x.cells[E].style.MozUserSelect = x.EditStatus ? "": "none"
        }
    }
    if (x.EditStatus) {
        x.oldClickEvent = x.onclick;
        x.onclick = function(j) {
            j = getEvent(j);
            cancelEvent(j)
        }
    } else {
        x.onclick = x.oldClickEvent
    }
    var m = x.ROMET("input");
    for (var F = 0; F < m.length; F++) {
        if (m[F].type == "text" && (m[F].value == unescape("%A0") || m[F].value == " ")) {
            m[F].value = ""
        }
        m[F].ondblclick = stopEvent;
        Effect.initCtrlStyle(m[F])
    }
    if (!x.EditStatus && K.cancelEdit) {
        K.cancelEdit(x, A)
    }
    if (x.EditStatus && K.beforeEdit) {
        K.beforeEdit(x, A)
    }
    return true
};
Page.onClick(function() {
    if (DataGrid.EditingRow != null) {
        DataGrid.changeStatus(DataGrid.EditingRow)
    }
});
DataGrid.edit = function(b, c) {
    c = ROME(c);
    if (!c) {
        alert("DataGrid.edit的参数必须是一个DataGrid对象")
    }
    var a = DataGrid.getSelectedRows(c);
    if (a.length < 1) {
        Dialog.alert("请先选择一条记录!");
        return
    }
    var d = a[0];
    d.ondblclick.apply(d, []);
    stopEvent(b)
};
DataGrid.editRow = function(g, b) {
    if (b) {
        var f = g.parentNode.parentNode;
        var c = f.DataSource;
        var a = g.rowIndex - 1;
        var d = new DataRow(c, a);
        b(d)
    } else {
        DataGrid.changeStatus(g)
    }
};
DataGrid.cancel = function(a) {
    var b = ROMEE.getParent("tr", a);
    DataGrid.changeStatus(b, "Cancel")
};
DataGrid.save = function(b, a, f) {
    if (DataGrid.EditingRow != null) {
        if (!DataGrid.changeStatus(DataGrid.EditingRow)) {
            return
        }
    }
    var h = ROME(b);
    var d = h.DataSource;
    var j = [];
    for (var g = 1; g < h.rows.length; g++) {
        if (h.rows[g].ModifyFlag) {
            j.push(d.Values[g - 1])
        }
    }
    if (j.length == 0) {
        Dialog.alert("数据未被修改过!");
        return
    }
    var c = new DataTable();
    c.Columns = d.Columns;
    c.Values = j;
    var k = new DataCollection();
    k.add("DT", c, "DataTable");
    Server.sendRequest(a, k,
    function(l) {
        if (l && l.Status == 0) {
            Dialog.alert(l.Message, f)
        } else {
            Dialog.alert("修改成功!",
            function() {
                for (var n = 1; n < h.rows.length; n++) {
                    if (h.rows[n].ModifyFlag) {
                        h.rows[n].ModifyFlag = false;
                        for (var m = 0; m < h.rows[n].cells.length; m++) {
                            h.rows[n].cells[m].style.backgroundColor = ""
                        }
                    }
                }
                h.OldValues = [];
                if (f) {
                    f()
                }
            })
        }
    })
};
DataGrid.discard = function(b, a) {
    DataGrid.loadData(b, a)
};
DataGrid.getRowDragProxy = function(g) {
    var d = g.parentNode.parentNode;
    var a = [];
    var f = ROMEE.getDimensions(d);
    var c = d.outerHTML.split(">")[0];
    c = c.replace(/width\=[\'\"].*?[\"\']/gi, "width='" + f.width + "'");
    c = c.replace(/align\=[\'\"].*?[\"\']/gi, "");
    a.push(c);
    a.push(g.outerHTML.split(">")[0] + " style='background-color:" + DataGrid.SelectedBgColor + "' >");
    for (var b = 0; b < g.cells.length; b++) {
        a.push(g.cells[b].outerHTML.split(">")[0]);
        a.push(" width='" + d.rows[0].cells[b].width + "' style='background-color:" + DataGrid.SelectedBgColor + "'");
        a.push(">");
        a.push(g.cells[b].innerHTML);
        a.push("</td>")
    }
    a.push("</tr></table>");
    return a.join("")
};
DataGrid.moveRow = function(f, b) {
    var d = f.parentNode.parentNode;
    if (isIE) {
        d.moveRow(f.rowIndex, b)
    } else {
        var c = f.outerHTML;
        d.deleteRow(f.rowIndex);
        var a = d.insertRow(b);
        a.outerHTML = c
    }
};
DataGrid.dragStart = function(a) {
    var d = this.parentNode;
    var b = d.parentNode.parentNode;
    var c = b.rows;
    DataGrid.onRowClick(d, a);
    DragManager.doDrag(a, DataGrid.getRowDragProxy(d))
};
DataGrid.dragEnd = function(evt) {
    var row = ROME(this);
    if (DragManager.DragSource.tagName != "TD") {
        return
    }
    var rowSource = ROME(DragManager.DragSource.parentNode);
    if (row.getParent("table") != rowSource.getParent("table")) {
        return
    }
    var table = row.parentNode.parentNode;
    var si = rowSource.rowIndex;
    var ni = row.rowIndex;
    if (ni > table.DataSource.Rows.length) {
        ni = table.DataSource.Rows.length
    }
    DataGrid.moveRow(rowSource, ni);
    var ds = table.DataSource;
    var vs = ds.Values;
    var arr = vs[si - 1];
    vs.splice(si - 1, 1);
    vs.insert(ni - 1, arr);
    for (var i = 0; i < vs.length; i++) {
        ds.Rows[i] = new DataRow(ds, i)
    }
    var arr = table.rows;
    for (var i = 1; i < arr.length; i++) {
        ROME(arr[i]).ROMET("input").each(function(ele) {
            if (ele.id && ele.id.toString().indexOf("_RowCheck") > 0) {
                ele.id = table.id + "_RowCheck" + i
            }
        })
    }
    row = table.rows[ni];
    DataGrid.onRowClick(row, evt);
    row.DefaultBgColor = "#fff";
    var afterDrag = table.getAttribute("afterDrag");
    if (afterDrag) {
        var func = eval(afterDrag);
        func.apply(row, [ni, si])
    }
};
DataGrid.dragOver = function(b, f) {
    if (isGecko) {
        return
    }
    var g = f || this;
    if (DragManager.DragSource.tagName != "TD") {
        return
    }
    for (var c = 0; c < g.cells.length; c++) {
        var a = g.cells[c];
        var d = isGecko ? document.defaultView.getComputedStyle(a, null) : a.currentStyle;
        if (g.rowIndex > DragManager.DragSource.parentNode.rowIndex) {
            if (!a.borderBottomStyle) {
                a.borderBottomStyle = d.borderBottomStyle;
                a.borderBottomColor = d.borderBottomColor;
                a.borderBottomWidth = d.borderBottomWidth;
                a.style.borderBottom = "dashed 2px #f90"
            }
        } else {
            if (!a.borderTopStyle) {
                a.borderTopStyle = d.borderTopStyle;
                a.borderTopColor = d.borderTopColor;
                a.borderTopWidth = d.borderTopWidth;
                a.style.borderTop = "dashed 2px #f90"
            }
        }
    }
};
DataGrid.dragOut = function(b) {
    if (isGecko) {
        return
    }
    var d = this;
    if (DragManager.DragSource.tagName != "TD") {
        return
    }
    for (var c = 0; c < d.cells.length; c++) {
        var a = d.cells[c];
        if (d.rowIndex > DragManager.DragSource.parentNode.rowIndex) {
            if (a.borderBottomStyle) {
                a.style.borderBottomStyle = a.borderBottomStyle;
                a.style.borderBottomColor = a.borderBottomColor;
                a.style.borderBottomWidth = a.borderBottomWidth;
                a.borderBottomStyle = ""
            }
        } else {
            if (a.borderTopStyle) {
                a.style.borderTopStyle = a.borderTopStyle;
                a.style.borderTopColor = a.borderTopColor;
                a.style.borderTopWidth = a.borderTopWidth;
                a.borderTopStyle = ""
            }
        }
    }
};
DataGrid.onSortHeadMouseOver = function(a) {
    a.className = "thOver"
};
DataGrid.onSortHeadMouseOut = function(a) {
    a.className = ""
};
var DataList = {};
DataList.init = function(a) {
    a = ROME(a);
    DataList.setParam(a, Constant.ID, a.id);
    DataList.setParam(a, Constant.Method, a.getAttribute("method"));
    DataList.setParam(a, Constant.TagBody, a.TagBody)
};
DataList.getParam = function(b, a) {
    b = ROME(b);
    return b.Params.get(a)
};
DataList.setParam = function(c, a, b) {
    c = ROME(c);
    if (!c.Params) {
        c.Params = new DataCollection()
    }
    c.Params.add(a, b)
};
DataList.loadData = function(ele, func) {
    ele = ROME(ele);
    var id = ele.id;
    Server.sendRequest("com.zving.framework.controls.DataListPage.doWork", ele.Params,
    function(response) {
        var html = response.get("HTML");
        var p = ele.parentNode;
        while (p) {
            if (p.tagName && p.tagName != "TR" && p.tagName != "TABLE" && p.tagName != "TBODY") {
                var phtml = p.innerHTML;
                var i1 = phtml.indexOf("<!--_ZVING_DATALIST_START_" + id + "-->");
                var i2 = phtml.indexOf("<!--_ZVING_DATALIST_END_" + id + "-->");
                i2 = phtml.indexOf("-->", i2) + 3;
                phtml = phtml.substring(0, i1) + html + phtml.substring(i2);
                p.innerHTML = phtml;
                break
            }
            p = p.parentNode
        }
        ele = ROME(id);
        p = ele.parentNode;
        while (p) {
            if (p.tagName) {
                break
            }
            p = p.parentNode
        }
        eval(p.getElementsByTagName("script")[0].text);
        ele = null;
        p = null;
        if (func) {
            func()
        }
    })
};
DataList.firstPage = function(a) {
    a = ROME(a);
    DataList.setParam(a, Constant.PageIndex, 0);
    DataList.loadData(a)
};
DataList.lastPage = function(d) {
    d = ROME(d);
    var c = DataList.getParam(d, Constant.PageTotal);
    var b = DataList.getParam(d, Constant.Size);
    var a = Math.ceil(parseInt(c) * 1 / parseInt(b));
    DataList.setParam(d, Constant.PageIndex, a - 1);
    DataList.loadData(d)
};
DataList.previousPage = function(b) {
    b = ROME(b);
    var a = DataList.getParam(b, Constant.PageIndex);
    DataList.setParam(b, Constant.PageIndex, parseInt(a) - 1);
    DataList.loadData(b)
};
DataList.nextPage = function(b) {
    b = ROME(b);
    var a = DataList.getParam(b, Constant.PageIndex);
    DataList.setParam(b, Constant.PageIndex, parseInt(a) + 1);
    DataList.loadData(b)
};
DataList.clear = function(a) {
    a = ROME(a);
    a.innerHTML = ""
};
ROMEE.computePositionEx = function(a, f) {
    var d = ROMEE.getPositionEx(a);
    var c = ROMEE.getDimensions(a);
    var b = ROMEE.getDimensions(f);
    return ROMEE.computePosition(d.x + c.width, d.y, d.x + c.width, d.y + c.height, "all", b.width, b.height, ROMEE.getTopLevelWindow())
};
var Calendar = {
    monthNames: ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"],
    weekNames: ["日", "一", "二", "三", "四", "五", "六"]
};
var TimeSelector = {};
var DateTime = {};
DateTime.getCurrentDate = function() {
    return DateTime.toString(new Date())
};
DateTime.getCurrentTime = function() {
    var c = new Date();
    var f = c.getHours();
    var b = c.getMinutes();
    var d = c.getSeconds();
    var a = [];
    a.push(f > 9 ? f: "0" + f);
    a.push(b > 9 ? b: "0" + b);
    a.push(d > 9 ? d: "0" + d);
    return a.join(":")
};
DateTime.toString = function(c) {
    var g = c.getFullYear();
    var b = c.getMonth() + 1;
    var f = c.getDate();
    var a = [];
    a.push(g);
    a.push(b > 9 ? b: "0" + b);
    a.push(f > 9 ? f: "0" + f);
    return a.join("-")
};
DateTime.parseDate = function(j) {
    var l = /^(\d{4})-(\d{1,2})-(\d{1,2})(\s(\d{1,2}):(\d{1,2})(:(\d{1,2}))?)?ROME/;
    if (!l.test(j)) {
        alert("DateTime.parseDate:错误的日期" + j)
    }
    l.exec(j);
    var k = RegExp.ROME1;
    var f = RegExp.ROME2;
    var g = RegExp.ROME3;
    var c = RegExp.ROME5;
    var b = RegExp.ROME6;
    var n = RegExp.ROME8;
    var a = new Date();
    a.setYear(k);
    a.setMonth(f - 1);
    a.setDate(g);
    if (c) {
        a.setHours(c);
        a.setMinutes(b)
    }
    if (n) {
        a.setSeconds(n)
    }
    return a
};
DateTime.initCtrl = function(ele) {
    ele = ROME(ele);
    var ztype = ele.ROMEA("ztype");
    if (ztype) {
        var str;
        var date = new Date();
        if (ztype.toLowerCase() == "date") {
            str = "Calendar"
        } else {
            if (ztype.toLowerCase() == "time") {
                str = "TimeSelector"
            } else {
                return
            }
        }
        var id = ele.id;
        ele.insertAdjacentHTML("afterEnd", "<img src='" + Server.ContextPath + "Framework/Images/" + str + ".gif' align='absmiddle' vspace='1' onmousedown=\"DateTime.onImageMouseDown(event,'" + str + "','" + id + "');\">");
        ele.attachEvent("onfocus",
        function() {
            eval(str + ".show('" + id + "')")
        });
        ele.onmousedown = DateTime.onMouseDown
    }
};
DateTime.onImageMouseDown = function(evt, str, id) {
    Calendar.close();
    TimeSelector.close();
    var pw = ROMEE.getTopLevelWindow();
    if (pw.DateTime && id == pw.DateTime.showingID) {
        return
    }
    eval(str + ".show(id)");
    stopEvent(evt)
};
DateTime.onMouseDown = function(a) {
    var b = ROMEE.getTopLevelWindow();
    if (this.id == b.DateTime.showingID && b.SourceWindow == window) {
        cancelEvent(a)
    }
};
Page.onMouseDown(function() {
    Calendar.close();
    TimeSelector.close()
});
function _LeftPad(b, d, a) {
    b = "" + b;
    return b.leftPad(d, a)
}
TimeSelector.setTime = function(a) {
    var f, b, n;
    if (a) {
        if (!/\d{1,2}\:\d{1,2}(\:\d{1,2})?/.test(a)) {
            Dialog.alert("错误的时间:" + a)
        }
        var g = a.split(":");
        f = parseInt(g[0]);
        b = parseInt(g[1]);
        n = parseInt(g[2])
    } else {
        var l = new Date();
        f = l.getHours();
        b = 0;
        n = 0
    }
    f = f > 23 ? 23 : f;
    b = b > 59 ? 59 : b;
    n = n > 59 ? 59 : n;
    b = b >= 10 ? b: "0" + b;
    n = n >= 10 ? n: "0" + n;
    var j = ROME("_TimeSelector_Frame").contentWindow;
    var g = j.ROME("divWrapper").getElementsByTagName("td");
    var k = g.length;
    for (var c = 0; c < k; c++) {
        g[c].className = ""
    }
    j.ROME("selectorHour").innerHTML = f;
    j.ROME("selectorMinute").innerHTML = b;
    j.ROME("selectorSecond").innerHTML = n;
    j.ROME("_TimeSelector_Tip").innerHTML = f + ":" + b + ":" + n;
    j.ROME("divHour").getElementsByTagName("td")[parseInt(f)].className = "selected";
    j.ROME("divMinute").getElementsByTagName("td")[parseInt(b)].className = "selected";
    j.ROME("divSecond").getElementsByTagName("td")[parseInt(n)].className = "selected";
    TimeSelector.showType("Hour");
    return true
};
TimeSelector.setTip = function() {
    ROME("_TimeSelector_Tip").innerText = [ROMEV("_TimeSelector_Hour"), ROMEV("_TimeSelector_Minute"), ROMEV("_TimeSelector_Second")].join(":")
};
TimeSelector.setNow = function() {
    ROMES(Control, DateTime.getCurrentTime());
    var _evt = Control.getAttribute("onchange");
    if (_evt) {
        eval(_evt)
    }
    TimeSelector.close()
};
TimeSelector.returnTime = function(b) {
    var c = ROME("_TimeSelector_Frame").contentWindow;
    if (b) {
        ROMES(c.Control, DateTime.getCurrentTime())
    } else {
        var a = [c.ROME("selectorHour").innerHTML, c.ROME("selectorMinute").innerHTML, c.ROME("selectorSecond").innerHTML];
        ROMES(c.Control, a.join(":"))
    }
    TimeSelector.close()
};
TimeSelector.showType = function(c) {
    var d = ROME("_TimeSelector_Frame").contentWindow;
    var a = ["Hour", "Minute", "Second"];
    for (var b = 0; b < a.length; b++) {
        d.ROME("selector" + a[b]).className = "selector";
        d.ROME("div" + a[b]).style.display = "none"
    }
    d.ROME("div" + c).style.display = "";
    d.ROME("selector" + c).className = "selector_current";
    TimeSelector.adjustSize()
};
TimeSelector.show = function(a, c) {
    var m = ROMEE.getTopLevelWindow();
    a = ROME(a);
    try {
        a.onfocus.apply(a, [])
    } catch(j) {}
    c = c ? c: ROMEV(a);
    var n;
    if (!m.ROME("_TimeSelector")) {
        n = m.document.createElement("div");
        n.id = "_TimeSelector";
        n.style.position = "absolute";
        n.style.zIndex = 999;
        n.innerHTML = "<iframe id='_TimeSelector_Frame' frameborder=0 scrolling=no width=194 height=153></iframe>";
        n.style.width = "194px";
        m.document.body.appendChild(n);
        n.style.display = "";
        var h = m.ROME("_TimeSelector_Frame").contentWindow;
        var l = h.document;
        l.open();
        var g = [];
        g.push("<style>");
        g.push("body {margin: 0px;}");
        g.push(".timetable {}");
        g.push(".timetable {position: absolute;	border-top: 1px solid #777;	border-right: 1px solid #555;border-bottom: 1px solid #444;font-family: tahoma,verdana,sans-serif;");
        g.push("border-left: 1px solid #666;font-size: 11px;cursor: default;background: #fff;}");
        g.push(".timetable .buttonNow {text-align: center;	background-color:#def;	border-right: 1px solid #999;color:#000;font-size: 12px;}");
        g.push(".timetable .buttonConfirm {text-align: center;	background-color:#def;	border-left: 1px solid #999;color:#000;font-size: 12px;}");
        g.push(".timetable .buttonclose {color:#06c;text-align: center;	background-color:#def;border-left: 1px solid #999;font-size:9px;width:16px}");
        g.push(".timetable td.selected {font-weight: bold;border: 1px solid #39f;	background: #c3e4FF;}");
        g.push(".timetable td.now {font-weight: bold; color: #03f;}");
        g.push(".timetable .tipnow {font-weight: bold;font-size:12px;color: #258;text-align: left;}");
        g.push(".timetable td.over {border:1px solid #06c;background: #EDFBD2;}");
        g.push(".selector {color:#258;padding:0 8px;border-right: 1px solid #999;border-left: 1px solid #999;background: #def;}");
        g.push(".selector_current {color:#fff;padding:0 8px;border-right: 1px solid #999;border-left: 1px solid #999;background: #ff8800;}");
        g.push(".wrapper {background-color:#fff;border-top: 1px solid #999;	border-bottom: 1px solid #999;text-align: center;}");
        g.push(".wrapper td{border: 1px solid #fff;	font-size: 12px;text-align: center;	color: #06c;}");
        g.push("</style>");
        g.push("<body><div class='timetable' id='_TimeSelector_Table'>");
        g.push("<table border='0' cellpadding='0' cellspacing='0' onselectstart='return false;' oncontextmenu='return false'>");
        g.push("<tr><td><table width='100%' border='0' cellpadding='0' cellspacing='0'>");
        g.push("<tr><td height='18' class='tipnow'>");
        g.push("<table width='100' height='100%' border='0' cellpadding='0' cellspacing='0'>");
        g.push("<tr class='tipnow'>");
        g.push("<td valign='middle' class='selector_current' id='selectorHour' onclick=\"TopWindow.TimeSelector.showType('Hour')\">0</td>");
        g.push("<td valign='middle' align='center'><span style='padding:3px;'>:</span></td>");
        g.push("<td valign='middle' class='selector' id='selectorMinute' onclick=\"TopWindow.TimeSelector.showType('Minute')\">00</td>");
        g.push("<td valign='middle' align='center'><span style='padding:3px;'>:</span></td>");
        g.push("<td valign='middle' class='selector' id='selectorSecond' onclick=\"TopWindow.TimeSelector.showType('Second')\">00</td>");
        g.push("</tr></table></td>");
        g.push("<td width='16'><table height='100%' height='100%' border='0' cellpadding='0' cellspacing='0'><tr><td class='buttonclose' title='取消' valign='middle' onclick=\"TopWindow.TimeSelector.close();this.style.backgroundColor='#def'\" onmouseover=\"this.style.backgroundColor='#9cf'\" onmouseout=\"this.style.backgroundColor='#def'\">×</td></tr></table></td>");
        g.push("</tr></table>");
        g.push("<div class='wrapper' id='divWrapper'>");
        g.push("<div id='divHour'>");
        g.push("<table width='210' height='60' border='0' cellpadding='0' cellspacing='0' style='font-size:13px'>");
        for (var d = 0; d < 24; d++) {
            if (d % 8 == 0) {
                g.push("<tr>")
            }
            if (d % 12 == 0) {
                g.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)' style='color: #e70'>" + d + "</td>")
            } else {
                g.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)'>" + d + "</td>")
            }
            if (d % 8 == 7) {
                g.push("</tr>")
            }
        }
        g.push("</table>");
        g.push("</div>");
        g.push("<div id='divMinute' style='display:none'>");
        var f = [];
        f.push("<table width='210' height='120' border='0' cellpadding='0' cellspacing='0'>");
        for (var d = 0; d < 60; d++) {
            if (d % 10 == 0) {
                f.push("<tr>");
                f.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)' style='color: #e70'>" + (d >= 10 ? d: "0" + d) + "</td>")
            } else {
                f.push("<td onclick='TopWindow.TimeSelector.onClick(this)' onmouseover='TopWindow.TimeSelector.onMouseOver(this)' onmouseout='TopWindow.TimeSelector.onMouseOut(this)'>" + (d >= 10 ? d: "0" + d) + "</td>")
            }
            if (d % 10 == 9) {
                f.push("</tr>")
            }
        }
        f.push("</table>");
        f.push("</div>");
        g.push(f.join("\n"));
        g.push("<div id='divSecond' style='display:none'>");
        g.push(f.join("\n"));
        g.push("</div>");
        g.push("<table width='100%' border='0' align='left' cellpadding='0' cellspacing='0'>");
        g.push("<tr>");
        g.push("<td width='17%' height='20' class='buttonNow' onclick=\"TopWindow.TimeSelector.returnTime(true);this.style.backgroundColor='#def'\" onmouseover=\"this.style.backgroundColor='#9cf'\" onmouseout=\"this.style.backgroundColor='#def'\">现在</td>");
        g.push("<td width='66%' style='font-size:11px;background-color:#fff6cc;font-weight:bold;color:#258;' id='_TimeSelector_Tip' align='center'>0:00:00</td>");
        g.push("<td width='17%' height='20' class='buttonConfirm' onclick=\"TopWindow.TimeSelector.returnTime();this.style.backgroundColor='#def'\" onmouseover=\"this.style.backgroundColor='#9cf'\" onmouseout=\"this.style.backgroundColor='#def'\">确定</td>");
        g.push("</tr>");
        g.push("</table>");
        g.push("</td>");
        g.push("</tr>");
        g.push("</table>");
        g.push("</div></body>");
        g.push("<script>function ROME(ele){return document.getElementById(ele);};function setTime(){if(!TopWindow.TimeSelector.setTime(Control.value)){alert('时间填写错误!');}TopWindow.TimeSelector.adjustSize();}<\/script>");
        l.write(g.join("\n"));
        l.close();
        h.Control = a;
        h.TopWindow = m;
        h.setTime()
    } else {
        n = m.ROME("_TimeSelector");
        n.show();
        var b = m.ROME("_TimeSelector_Frame");
        b.show();
        b.contentWindow.Control = a;
        b.contentWindow.setTime()
    }
    var k = ROMEE.computePositionEx(a, n);
    n.style.top = k.y + "px";
    n.style.left = k.x + "px";
    m.DateTime.showingID = a.id;
    m.SourceWindow = window;
    Misc.lockScroll(window)
};
TimeSelector.onMouseOver = function(b) {
    var c = ROME("_TimeSelector_Frame").contentWindow;
    var d = b.parentNode.parentNode.parentNode.parentNode.id;
    var a = [c.ROME("selectorHour").innerHTML, c.ROME("selectorMinute").innerHTML, c.ROME("selectorSecond").innerHTML];
    if (d == "divHour") {
        a[0] = b.innerHTML
    } else {
        if (d == "divMinute") {
            a[1] = b.innerHTML
        } else {
            if (d == "divSecond") {
                a[2] = b.innerHTML
            }
        }
    }
    c.ROME("_TimeSelector_Tip").innerHTML = a.join(":");
    ROMEE.addClassName("over", true, b)
};
TimeSelector.onMouseOut = function(a) {
    ROMEE.removeClassName("over", a)
};
TimeSelector.onClick = function(a) {
    var c = ROME("_TimeSelector_Frame").contentWindow;
    ROMEE.addClassName("selected", true, a);
    var d = a.parentNode.parentNode.parentNode.parentNode.id;
    if (d == "divHour") {
        c.ROME("divHour").getElementsByTagName("td")[parseInt(c.ROME("selectorHour").innerHTML)].className = "";
        c.ROME("selectorHour").innerHTML = a.innerHTML;
        TimeSelector.showType("Minute")
    } else {
        if (d == "divMinute") {
            c.ROME("divMinute").getElementsByTagName("td")[parseInt(c.ROME("selectorMinute").innerHTML)].className = "";
            c.ROME("selectorMinute").innerHTML = a.innerHTML;
            TimeSelector.showType("Second")
        } else {
            if (d == "divSecond") {
                c.ROME("divSecond").getElementsByTagName("td")[parseInt(c.ROME("selectorSecond").innerHTML)].className = "";
                c.ROME("selectorSecond").innerHTML = a.innerHTML;
                TimeSelector.returnTime()
            }
        }
    }
    var b = ROMEE.getTopLevelWindow();
    Misc.unlockScroll(b.SourceWindow)
};
TimeSelector.close = function() {
    var b = ROMEE.getTopLevelWindow();
    if (b.DateTime && b.ROME("_TimeSelector") && b.ROME("_TimeSelector").visible()) {
        var c = b.ROME("_TimeSelector_Frame");
        try {
            c.contentWindow.Control.onblur.apply(c.contentWindow.Control, [])
        } catch(a) {}
        ROMEE.hide(b.ROME("_TimeSelector"));
        Misc.unlockScroll(b.SourceWindow);
        b.SourceWindow = null;
        b.DateTime.showingID = false
    }
};
Calendar.showYearSelector = function() {
    var f = ROME("_Calendar_Frame").contentWindow;
    var d = f.ROME("_Calendar_Year"),
    c = f.ROME("_Calendar_YearSelector");
    d.style.display = "none";
    c.style.display = "";
    var b = d.Year;
    for (var a = b > 50 ? b - 50 : 0; a <= 50 + parseInt(b); a++) {
        c.options.add(new Option(a, a))
    }
    c.focus();
    c.selectedIndex = 50;
    Calendar.adjustSize()
};
Calendar.showMonthSelector = function() {
    var c = ROME("_Calendar_Frame").contentWindow;
    var b = c.ROME("_Calendar_Month"),
    a = c.ROME("_Calendar_MonthSelector");
    b.style.display = "none";
    a.style.display = "";
    a.focus();
    a.selectedIndex = b.Month;
    Calendar.adjustSize()
};
Calendar.hideYearSelector = function() {
    var d = ROME("_Calendar_Frame").contentWindow;
    var c = d.ROME("_Calendar_Year"),
    b = d.ROME("_Calendar_YearSelector");
    c.style.display = "";
    b.style.display = "none";
    for (var a = b.options.length; a > -1; a--) {
        b.remove(a)
    }
    Calendar.adjustSize()
};
Calendar.hideMonthSelector = function() {
    var a = ROME("_Calendar_Frame").contentWindow;
    a.ROME("_Calendar_Month").style.display = "";
    a.ROME("_Calendar_MonthSelector").style.display = "none";
    Calendar.adjustSize()
};
Calendar.adjustSize = function() {
    var b = ROME("_Calendar_Frame").contentWindow;
    var a = ROMEE.getDimensions(b.ROME("_Calendar_Table"));
    b.frameElement.height = a.height + 1;
    b.frameElement.width = a.width + 3
};
TimeSelector.adjustSize = function() {
    var b = ROME("_TimeSelector_Frame").contentWindow;
    var a = ROMEE.getDimensions(b.ROME("_TimeSelector_Table"));
    b.frameElement.height = a.height;
    b.frameElement.width = a.width + 3
};
Calendar.onYearSelectorChange = function() {
    var d = ROME("_Calendar_Frame").contentWindow;
    var c = d.ROME("_Calendar_Year"),
    b = d.ROME("_Calendar_YearSelector");
    c.Year = b.value;
    var a = c.Year + "-" + _LeftPad(d.ROME("_Calendar_Month").Month + 1, "0", 2) + "-01";
    Calendar.setDate(a);
    c.style.display = "";
    b.style.display = "none";
    Calendar.adjustSize()
};
Calendar.onMonthSelectorChange = function() {
    var d = ROME("_Calendar_Frame").contentWindow;
    var c = d.ROME("_Calendar_Month"),
    b = d.ROME("_Calendar_MonthSelector");
    c.Month = parseInt(b.value);
    var a = d.ROME("_Calendar_Year").Year + "-" + _LeftPad(c.Month + 1, "0", 2) + "-01";
    Calendar.setDate(a);
    c.style.display = "";
    b.style.display = "none";
    Calendar.adjustSize()
};
Calendar.getDateString = function(a) {
    var j = ROME("_Calendar_Frame").contentWindow;
    var f = j.Control;
    var h = f.ROMEA("format");
    if (!h) {
        h = "yyyy-MM-dd"
    }
    if (a.Day) {
        var b = a.Day,
        g = j.ROME("_Calendar_Month").Month,
        c = j.ROME("_Calendar_Year").Year;
        if (b < 0) {
            b = -b;
            if (g == 0) {
                g = 11;
                c--
            } else {
                g--
            }
        } else {
            if (b > 32) {
                b -= 40;
                if (g == 11) {
                    g = 0;
                    c++
                } else {
                    g++
                }
            }
        }
        h = h.replace("yyyy", c);
        h = h.replace("MM", _LeftPad(g + 1, "0", 2));
        h = h.replace("dd", _LeftPad(b, "0", 2));
        return h
    } else {
        if (a.id == "_Calendar_Today") {
            var k = new Date();
            h = h.replace("yyyy", isGecko ? k.getYear() + 1900 : k.getYear());
            h = h.replace("MM", _LeftPad(k.getMonth() + 1, "0", 2));
            h = h.replace("dd", _LeftPad(k.getDate(), "0", 2));
            return h
        } else {
            return false
        }
    }
};
Calendar.onMouseOver = function(a) {
    var b = ROME("_Calendar_Frame").contentWindow;
    a.oldCssText = a.style.cssText;
    var c = Calendar.getDateString(a);
    if (c) {
        b.ROME("_Calendar_Tip").innerHTML = c
    }
    if (a.Day) {
        a.style.cssText = "border-top: 1px solid #06c;border-right: 1px solid #06c;border-bottom: 1px solid #06c;border-left: 1px solid #06c;padding: 2px 2px 0px 2px;background: #EDFBD2;"
    } else {
        a.style.cssText = "background: #9cf;"
    }
    Calendar.isMouseOut = false
};
Calendar.onMouseOut = function(a) {
    a.style.cssText = a.oldCssText
};
Calendar.returnDate = function(cell) {
    var win = ROME("_Calendar_Frame").contentWindow;
    var Control = win.Control;
    ROMES(Control, Calendar.getDateString(cell));
    var _evt = Control.ROMEA("onchange");
    if (_evt) {
        eval(_evt)
    }
    cell.style.cssText = cell.oldCssText;
    Calendar.close()
};
Calendar.previousYear = function() {
    var b = ROME("_Calendar_Frame").contentWindow;
    var a = (--b.ROME("_Calendar_Year").Year) + "-" + _LeftPad(++b.ROME("_Calendar_Month").Month, "0", 2) + "-01";
    Calendar.setDate(a);
    Calendar.adjustSize()
};
Calendar.nextYear = function() {
    var b = ROME("_Calendar_Frame").contentWindow;
    var a = (++b.ROME("_Calendar_Year").Year) + "-" + _LeftPad(++b.ROME("_Calendar_Month").Month, "0", 2) + "-01";
    Calendar.setDate(a);
    Calendar.adjustSize()
};
Calendar.previousMonth = function() {
    var d = ROME("_Calendar_Frame").contentWindow;
    var c = d.ROME("_Calendar_Month").Month,
    b = d.ROME("_Calendar_Year").Year;
    if (c == 0) {
        c = 11;
        b--
    } else {
        c--
    }
    var a = "" + b + "-" + _LeftPad(c + 1, "0", 2) + "-01";
    Calendar.setDate(a);
    Calendar.adjustSize()
};
Calendar.nextMonth = function() {
    var d = ROME("_Calendar_Frame").contentWindow;
    var c = d.ROME("_Calendar_Month").Month,
    b = d.ROME("_Calendar_Year").Year;
    if (c == 11) {
        c = 0;
        b++
    } else {
        c++
    }
    var a = "" + b + "-" + _LeftPad(c + 1, "0", 2) + "-01";
    Calendar.setDate(a);
    Calendar.adjustSize()
};
Calendar.setDate = function(w) {
    var b = ROME("_Calendar_Frame").contentWindow;
    var z = b.Control;
    var f;
    if (!w) {
        var v = new Date();
        f = [isGecko ? v.getYear() + 1900 : v.getYear(), v.getMonth() + 1, v.getDate()]
    } else {
        var s = z.ROMEA("format");
        if (!s) {
            s = "yyyy-MM-dd"
        }
        f = new Array(3);
        var k = s.indexOf("yyyy");
        if (k >= 0) {
            f[0] = w.substr(k, 4)
        } else {
            alert("日期格式错误，没有年!")
        }
        var u = s.indexOf("MM");
        if (u >= 0) {
            f[1] = w.substr(u, 2)
        } else {
            alert("日期格式错误，没有月!")
        }
        var t = s.indexOf("dd");
        if (t >= 0) {
            f[2] = w.substr(t, 2)
        } else {
            alert("日期格式错误，没有日!")
        }
    }
    var l = f[0];
    var x = true;
    if (isNaN(l)) {
        l = "2000";
        x = false
    }
    b.ROME("_Calendar_Year").innerHTML = l;
    b.ROME("_Calendar_Year").Year = l;
    var y = f.length > 1 ? f[1] - 1 : 0;
    if (f.length == 1) {
        x = false
    }
    if (isNaN(y) || y < 0 || y > 11) {
        y = 0;
        x = false
    }
    b.ROME("_Calendar_Month").innerHTML = Calendar.monthNames[y];
    b.ROME("_Calendar_Month").Month = y;
    var q = f.length > 2 ? f[2] : 0;
    var A = new Date();
    A.setYear(l);
    A.setMonth(y);
    A.setDate(1);
    var m = A.getDay();
    if (y == 0) {
        A.setYear(l - 1);
        A.setMonth(11)
    } else {
        A.setYear(l);
        A.setMonth(y - 1)
    }
    var c = [],
    r,
    n;
    for (r = 28; r < 33; r++) {
        A.setDate(r);
        if (A.getMonth() == y) {
            for (n = r - m; n < r; n++) {
                c.push([0, n])
            }
            break
        }
    }
    A.setYear(l);
    A.setMonth(y);
    for (r = 1; r < 32; r++) {
        if (r >= 28) {
            A.setDate(r);
            if (A.getMonth() != y) {
                break
            }
        }
        if ((m + r) % 7 == 0 || (m + r) % 7 == 1) {
            c.push([1, r])
        } else {
            c.push([2, r])
        }
    }
    for (n = 0; n < 7 - ((r - 1 + m) % 7 == 0 ? 7 : (r - 1 + m) % 7); n++) {
        c.push([3, n + 1])
    }
    var h = [],
    g = b.ROME("_Calendar_Table").rows;
    for (r = 0; r < c.length; r++) {
        var o = c[r][0];
        var a = g[Math.floor(2 + r / 7)].cells[r % 7];
        a.innerHTML = c[r][1];
        if (o == 0) {
            a.className = "day disabled";
            a.Day = -c[r][1]
        }
        if (o == 3) {
            a.className = "day disabled";
            a.Day = 40 + c[r][1]
        }
        if (o == 1) {
            a.className = "day weekend";
            a.Day = c[r][1]
        }
        if (o == 2) {
            a.className = "day";
            a.Day = c[r][1]
        }
    }
    for (n = 4; n < 6; n++) {
        if (n > c.length / 7 - 1) {
            b.ROME("_Calendar_DayRow" + n).style.display = "none"
        } else {
            b.ROME("_Calendar_DayRow" + n).style.display = ""
        }
    }
    if (f.length == 2) {
        x = false
    }
    if (isNaN(q) || q < 1 || q > 31) {
        q = 1;
        x = false
    }
    b.ROME("_Calendar_Day" + (q - 1 + m)).className += " selected";
    b.ROME("_Calendar_Tip").innerHTML = l + "-" + _LeftPad(y + 1, "0", 2) + "-" + _LeftPad(q, "0", 2);
    A = new Date();
    if ((isGecko ? A.getYear() + 1900 : A.getYear()) == l && A.getMonth() == y) {
        b.ROME("_Calendar_Day" + (A.getDate() - 1 + m)).className += " today"
    }
    return x
};
Calendar.show = function(a, c) {
    var n = ROMEE.getTopLevelWindow();
    a = ROME(a);
    try {
        a.onfocus.apply(a, [])
    } catch(k) {}
    c = c ? c: ROMEV(a);
    var o;
    if (!n.ROME("_Calendar")) {
        o = n.document.createElement("div");
        o.id = "_Calendar";
        o.style.position = "absolute";
        o.style.zIndex = 999;
        o.innerHTML = "<iframe id='_Calendar_Frame' frameborder=0 scrolling=no width=194 height=153></iframe>";
        o.style.width = "194px";
        n.document.body.appendChild(o);
        o.style.display = "";
        var h = n.ROME("_Calendar_Frame").contentWindow;
        var m = h.document;
        m.open();
        var g = [];
        g.push("<style>");
        g.push(".nostyle{}");
        g.push(".calendar {position: absolute; border-top: 1px solid #777; border-right: 1px solid #555; border-bottom: 0px solid #444; border-left: 1px solid #666; font-size: 11px; cursor: default; background: #ddd;}");
        g.push(".calendar table { font-size: 11px; color: #06c; cursor: default; background: #def; font-family: tahoma,verdana,sans-serif;}");
        g.push(".daynames{color:555;}");
        g.push(".calendar .button {text-align: center;padding: 1px;border-top: 1px solid #fff; border-right: 1px solid #999; border-bottom: 1px solid #999; border-left: 1px solid #fff;}");
        g.push(".calendar .buttontoday {text-align: center; padding: 1px; border-top: 1px solid #999; border-right: 1px solid #999; border-bottom: 1px solid #666; color:#000;}");
        g.push(".calendar .buttonclose {text-align: center; padding: 1px; border-top: 1px solid #fff; border-right: 0px solid #999; border-bottom: 1px solid #999; border-left: 1px solid #fff;}");
        g.push(".calendar thead .title {font-weight: bold; border-right: 1px solid #999; border-bottom: 1px solid #999; background: #B3D4FF; color: #258; text-align: center;}");
        g.push(".calendar thead .name {border-bottom: 1px solid #ccc; padding: 2px; text-align: right; background: #E8EEF4;}");
        g.push(".calendar .weekend {color: #e70;}");
        g.push(".calendar tbody .day {width: 2em; text-align: right; padding: 2px 4px 2px 2px; background: #fff;}");
        g.push(".calendar tbody td.selected {font-weight: bold; border-top: 1px solid #06c; border-right: 1px solid #06c; border-bottom: 1px solid #06c; border-left: 1px solid #06c; padding: 2px 2px 0px 2px; background: #B3D4FF;}");
        g.push(".calendar tbody td.weekend {color: #e70;}");
        g.push(".calendar tbody td.today {font-weight: bold;color: #03f;}");
        g.push(".calendar tbody .disabled { color: #999; }");
        g.push(".calendar tfoot .tiptoday {padding: 2px; border-top: 1px solid #999; border-right: 0px solid #999; border-bottom: 1px solid #666; border-left: 0px solid #999; background: #fff6cc; font-weight: bold; color: #258; text-align: center;}");
        g.push("body {margin: 0px; }");
        g.push("</style>");
        g.push("<div class='calendar'>");
        g.push("<TABLE oncontextmenu='return false' onselectstart='return false;' id=_Calendar_Table cellSpacing=0 cellPadding=0 width=190>");
        g.push("  <THEAD>");
        g.push("    <TR><TD colSpan=7>");
        g.push("      <TABLE class=nostyle cellSpacing=0 cellPadding=0 width='100%'>");
        g.push("        <TBODY>");
        g.push("          <TR height=20>");
        g.push("            <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.previousYear(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8249;</TD>");
        g.push("            <TD class=title><DIV id=_Calendar_Year style='WIDTH: 63px' onclick=TopWindow.Calendar.showYearSelector();>2006</DIV>");
        g.push("              <SELECT id=_Calendar_YearSelector onBlur='TopWindow.Calendar.hideYearSelector()' style='DISPLAY: none; FONT-SIZE: 11px; WIDTH: 63px' onChange='TopWindow.Calendar.onYearSelectorChange()'>");
        g.push("              </SELECT></TD>");
        g.push("            <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.nextYear(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8250;</TD>");
        g.push("            <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.previousMonth(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8249;</TD>");
        g.push("            <TD class=title><DIV id=_Calendar_Month style='WIDTH: 63px' onclick=TopWindow.Calendar.showMonthSelector();>12月</DIV>");
        g.push("              <SELECT id=_Calendar_MonthSelector onblur=TopWindow.Calendar.hideMonthSelector() style='DISPLAY: none; FONT-SIZE: 11px; WIDTH: 63px' onchange=TopWindow.Calendar.onMonthSelectorChange()>");
        g.push("                <OPTION value=0 selected>1月</OPTION>");
        for (var f = 1; f < 12; f++) {
            g.push("                <OPTION value=" + f + ">" + (f + 1) + "月</OPTION>")
        }
        g.push("              </SELECT></TD>");
        g.push("        <TD class=button onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.nextMonth(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=12>&#8250;</TD>");
        g.push("          <TD class=buttonclose onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.close(); onmouseout=TopWindow.Calendar.onMouseOut(this); width=16>×</TD>");
        g.push("        </TR>");
        g.push("        </TBODY>");
        g.push("      </TABLE>");
        g.push("      </TD>");
        g.push("    </TR>");
        g.push("    <TR class=daynames>");
        g.push("      <TD class='name weekend'>日</TD>");
        g.push("      <TD class=name>一</TD>");
        g.push("      <TD class=name>二</TD>");
        g.push("      <TD class=name>三</TD>");
        g.push("      <TD class=name>四</TD>");
        g.push("      <TD class=name>五</TD>");
        g.push("      <TD class='name weekend'>六</TD>");
        g.push("    </TR>");
        g.push("  </THEAD>");
        g.push("  <TBODY id=_Calendar_Body>");
        for (var f = 0; f < 6; f++) {
            g.push("    <TR class=daysrow id=_Calendar_DayRow" + f + ">");
            for (var d = 0; d < 7; d++) {
                g.push("      <TD class=day id=_Calendar_Day" + (f * 7 + d) + " onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.returnDate(this); onmouseout=TopWindow.Calendar.onMouseOut(this);>&nbsp;</TD>")
            }
            g.push("    </TR>")
        }
        g.push("  </TBODY>");
        g.push("  <TFOOT>");
        g.push("    <TR class=footrow>");
        g.push("      <TD class=buttontoday id=_Calendar_Today onmouseover=TopWindow.Calendar.onMouseOver(this); onclick=TopWindow.Calendar.returnDate(this); onmouseout=TopWindow.Calendar.onMouseOut(this); colSpan=2>今日</TD>");
        g.push("      <TD class=tiptoday id=_Calendar_Tip align=middle colSpan=5>&nbsp;</TD>");
        g.push("    </TR>");
        g.push("  </TFOOT>");
        g.push("</TABLE>");
        g.push("</div>");
        g.push("<script>function ROME(ele){return document.getElementById(ele);};function setDate(){if(!TopWindow.Calendar.setDate(Control.value)){alert('日期填写错误!');}TopWindow.Calendar.adjustSize();}<\/script>");
        m.write(g.join("\n"));
        m.close();
        h.Control = a;
        h.TopWindow = n;
        h.setDate()
    } else {
        o = n.ROME("_Calendar");
        o.show();
        var b = n.ROME("_Calendar_Frame");
        b.show();
        b.contentWindow.Control = a;
        b.contentWindow.setDate()
    }
    var l = ROMEE.computePositionEx(a, o);
    o.style.top = l.y + "px";
    o.style.left = l.x + "px";
    n.DateTime.showingID = a.id;
    n.SourceWindow = window;
    Misc.lockScroll(window)
};
Calendar.close = function() {
    var b = ROMEE.getTopLevelWindow();
    if (b.DateTime && b.ROME("_Calendar") && b.ROME("_Calendar").visible()) {
        var c = b.ROME("_Calendar_Frame");
        try {
            c.contentWindow.Control.onblur.apply(c.contentWindow.Control, [])
        } catch(a) {}
        ROMEE.hide(b.ROME("_Calendar"));
        Misc.unlockScroll(b.SourceWindow);
        b.SourceWindow = null;
        b.DateTime.showingID = false
    }
};
function Dialog(a) {
    if (!a) {
        alert("错误的Dialog ID！");
        return
    }
    this.ID = a;
    this.isModal = true;
    this.Width = 400;
    this.Height = 300;
    this.Top = 0;
    this.Left = 0;
    this.ParentWindow = null;
    this.onLoad = null;
    this.Window = null;
    this.Title = "";
    this.URL = null;
    this.DialogArguments = {};
    this.WindowFlag = false;
    this.Message = null;
    this.MessageTitle = null;
    this.ShowMessageRow = false;
    this.ShowButtonRow = true;
    this.Icon = null
}
Dialog._Array = [];
Dialog.prototype.showWindow = function() {

    if (isIE) {
        this.ParentWindow.showModalessDialog(this.URL, this.DialogArguments, "dialogWidth:" + this.Width + ";dialogHeight:" + this.Height + ";help:no;scroll:no;status:no")
    }
    if (isGecko) {
        var b = "location=no,menubar=no,status=no;toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,resizable=no";
        this.Window = this.ParentWindow.open("", this.URL, b, true);
        var a = this.Window;
        if (!a) {
            alert("发现弹出窗口被阻止，请更改浏览器设置，以便正常使用本功能!");
            return
        }
        a.moveTo(this.Left, this.Top);
        a.resizeTo(this.Width, this.Height + 30);
        a.focus();
        a.location.href = this.URL;
        a.Parent = this.ParentWindow;
        a.dialogArguments = this.DialogArguments
    }
};
Dialog.prototype.show = function() {
	$('select').css('visibility','hidden');
    var m = ROMEE.getTopLevelWindow();
    var k = m.document;
    var c = k.compatMode == "BackCompat" ? k.body.clientWidth: k.documentElement.clientWidth;
    var a = k.compatMode == "BackCompat" ? k.body.scrollHeight: k.documentElement.scrollHeight;
    var a1 = k.compatMode == "BackCompat" ? k.body.clientHeight: k.documentElement.clientHeight;
    var l = Math.max(k.documentElement.scrollLeft, k.body.scrollLeft);
    var d = Math.max(k.documentElement.scrollTop, k.body.scrollTop);
    if (!this.ParentWindow) {
        this.ParentWindow = window
    }
    this.DialogArguments._DialogInstance = this;
    this.DialogArguments.ID = this.ID;
    if (!this.Height) {
        this.Height = this.Width / 2
    }
    if (this.Top == 0) {
        this.Top = (a1 - this.Height - 30) / 2 + d - 8
    }
    if (this.Left == 0) {
        this.Left = (c - this.Width - 12) / 2 + l
    }
    if (this.ShowButtonRow) {
        this.Top -= 18
    }
    if (this.WindowFlag) {
        this.showWindow();
        return
    }
    var f = [];
    f.push("<table style='-moz-user-select:none;' oncontextmenu='stopEvent(event);' onselectstart='stopEvent(event);' border='0' cellpadding='0' cellspacing='0' width='" + (this.Width + 26) + "'>");
    f.push("  <tr style='cursor:move;'>");
    f.push("    <td width='13' height='33' style=\"background-image:url(/" + Server.ContextPath + "/images/dialog/dialog_lt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + "/images/dialog/dialog_lt.png', sizingMethod='crop');\"><div style='width:13px;'></div></td>");
    f.push("    <td height='33' style=\"background-image:url(/" + Server.ContextPath + "/images/dialog/dialog_ct.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + '/images/dialog/dialog_ct.png\', sizingMethod=\'crop\');"><div style="float:left;font-weight:bold; color:#FFFFFF; padding:9px 0 0 4px;"><img src="/' + Server.ContextPath + '/images/dialog/icon_dialog.gif" align="absmiddle">&nbsp;' + this.Title + "</div>");
    f.push('      <div style="position: relative;cursor:pointer; float:right; margin:5px 0 0; _margin:4px 0 0;height:17px; width:28px; background-image:url(/' + Server.ContextPath + '/images/dialog/dialog_closebtn.gif)" onMouseOver="this.style.backgroundImage=\'url(/' + Server.ContextPath + "/images/dialog/dialog_closebtn_over.gif)'\" onMouseOut=\"this.style.backgroundImage='url(/" + Server.ContextPath + "/images/dialog/dialog_closebtn.gif)'\" drag='false' onClick=\"Dialog.getInstance('" + this.ID + "').CancelButton.onclick.apply(Dialog.getInstance('" + this.ID + "').CancelButton,[]);\"></div></td>");
    f.push("    <td width='13' height='33' style=\"background-image:url(/" + Server.ContextPath + "/images/dialog/dialog_rt.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + "/images/dialog/dialog_rt.png', sizingMethod='crop');\"><div style=\"width:13px;\"></div></td>");
    f.push("  </tr>");
    f.push("  <tr drag='false'><td width='13' style=\"background-image:url(/" + Server.ContextPath + "/images/dialog/dialog_mlm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + "/images/dialog/dialog_mlm.png', sizingMethod='crop');\"></td>");
    f.push("    <td align='center' valign='top'>");
    f.push("    <table width='100%' height='100%' border='0' cellpadding='0' cellspacing='0' bgcolor='#FFFFFF'>");
    f.push("        <tr id='_MessageRow_" + this.ID + "' style='display:none'>");
    f.push("          <td height='50' valign='top'><table id='_MessageTable_" + this.ID + "' width='100%' border='0' cellspacing='0' cellpadding='8' style=\" background:#EAECE9 url(/" + Server.ContextPath + '/images/dialog/dialog_bg.jpg) no-repeat right top;">');
    f.push("              <tr><td width='25' height='50' align='right'><img id='_MessageIcon_" + this.ID + "' src='/" + Server.ContextPath + "/images/dialog/window.gif' width='32' height='32'></td>");
    f.push("                <td align='left' style='line-height:16px;'>");
    f.push("                <h5 class='fb' id='_MessageTitle_" + this.ID + "'>&nbsp;</h5>");
    f.push("                <div id='_Message_" + this.ID + "'>&nbsp;</div></td>");
    f.push("              </tr></table></td></tr>");
    f.push("        <tr><td align='center' valign='middle'>");
    f.push("          <iframe src='");
    if (this.URL.indexOf(":") == -1) {
        f.push(this.URL)
    } else {
        f.push(this.URL)
    }
    f.push("' id='_DialogFrame_" + this.ID + "' allowTransparency='true'  width='" + this.Width + "' height='" + this.Height + "' frameborder='0' style=\"background-color: #transparent; border:none;\"></iframe></td></tr>");
    f.push("        <tr drag='false' id='_ButtonRow_" + this.ID + "'><td height='36'>");
    f.push("            <div id='_DialogButtons_" + this.ID + "' style='text-align:right; border-top:#dadee5 1px solid; padding:8px 20px; background-color:#f6f6f6;'>");
    f.push("           	<input id='_ButtonOK_" + this.ID + "'  type='button' value='确 定'>");
    f.push("           	<input id='_ButtonCancel_" + this.ID + "'  type='button' onclick=\"Dialog.getInstance('" + this.ID + "').close();\" value='取 消'>");
    f.push("            </div></td></tr>");
    f.push("      </table></td>");
    f.push("    <td width='13' style=\"background-image:url(/" + Server.ContextPath + "/images/dialog/dialog_mrm.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + "/images/dialog/dialog_mrm.png', sizingMethod='crop');\"></td></tr>");
    f.push("  <tr><td width='13' height='13' style=\"background-image:url(/" + Server.ContextPath + "/images/dialog/dialog_lb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + "/images/dialog/dialog_lb.png', sizingMethod='crop');\"></td>");
    f.push('    <td style="background-image:url(/' + Server.ContextPath + "/images/dialog/dialog_cb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + "/images/dialog/dialog_cb.png', sizingMethod='crop');\"></td>");
    f.push("    <td width='13' height='13' style=\"background-image:url(/" + Server.ContextPath + "/images/dialog/dialog_rb.png) !important;background-image: none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='/" + Server.ContextPath + "/images/dialog/dialog_rb.png', sizingMethod='crop');\"></td>");
    f.push("  </tr></table>");
    //alert(this.ID);
    this.TopWindow = m;
    var j = m.ROME("_DialogBGDiv");
    if (!j) {
        j = m.document.createElement("div");
        j.id = "_DialogBGDiv";
        ROMEE.hide(j);
        m.ROMET("body")[0].appendChild(j)
    }
    var b = m.ROME("_DialogDiv_" + this.ID);
    if (!b) {
        b = m.document.createElement("div");
        ROMEE.hide(b);
        b.id = "_DialogDiv_" + this.ID;
        b.className = "dialogdiv";
        b.setAttribute("dragStart", "Dialog.dragStart");
        m.ROMET("body")[0].appendChild(b)
    }
    b.onmousedown = function(o) {
        var n = ROMEE.getTopLevelWindow();
        n.DragManager.onMouseDown(o || n.event, this)
    };
    this.DialogDiv = b;
    b.innerHTML = f.join("\n");
    m.ROME("_DialogFrame_" + this.ID).DialogInstance = this;
    m.Effect.initCtrlStyle(m.ROME("_ButtonOK_" + this.ID));
    m.Effect.initCtrlStyle(m.ROME("_ButtonCancel_" + this.ID));
    this.OKButton = m.ROME("_ButtonOK_" + this.ID);
    this.CancelButton = m.ROME("_ButtonCancel_" + this.ID);
    if (this.ShowMessageRow) {
        ROMEE.show(m.ROME("_MessageRow_" + this.ID));
        if (this.MessageTitle) {
            m.ROME("_MessageTitle_" + this.ID).innerHTML = this.MessageTitle
        }
        if (this.Message) {
            m.ROME("_Message_" + this.ID).innerHTML = this.Message
        }
    }
    if (!this.ShowButtonRow) {
        m.ROME("_ButtonRow_" + this.ID).hide()
    }
    if (this.OKEvent) {
        this.OKButton.onclick = this.OKEvent
    }
    if (this.CancelEvent) {
        this.CancelButton.onclick = this.CancelEvent
    }
    if (!this.AlertFlag) {
        ROMEE.show(j)
    } else {
        j = m.ROME("_AlertBGDiv");
        if (!j) {
            j = m.document.createElement("div");
            j.id = "_AlertBGDiv";
            ROMEE.hide(j);
            m.ROMET("body")[0].appendChild(j);
            j.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:" + c + "px;height:" + a + "px;z-index:991"
        }
    }
    this.DialogDiv.style.cssText = "position:absolute; display:block;z-index:" + (this.AlertFlag ? 992 : 990) + ";left:" + this.Left + "px;top:" + this.Top + "px";
    if (!this.AlertFlag) {
        var g = window;
        var h = false;
        while (g != g.parent) {
            if (g._DialogInstance) {
                g._DialogInstance.DialogDiv.style.zIndex = 959;
                h = true;
                break
            }
            g = g.parent
        }
        if (!h) {
            j.style.cssText = "background-color:#333;position:absolute;left:0px;top:0px;opacity:0;filter:alpha(opacity=0);width:" + c + "px;height:" + a + "px;z-index:960"
        }
    }
    m.Dialog._Array.push(this.ID)
};
Dialog.hideAllFlash = function(f) {
    if (!f || !f.ROMET) {
        return
    }
    var d = f.ROMET("embed");
    for (var c = 0; c < d.length; c++) {
        try {
            d[c].OldStyle = d[c].style.display;
            d[c].style.display = "none"
        } catch(b) {}
    }
    var a = f.ROMET("iframe");
    for (var c = 0; a && c < a.length; c++) {
        Dialog.hideAllFlash(a[c].contentWindow)
    }
};
Dialog.showAllFlash = function(f) {
    if (!f || !f.ROMET) {
        return
    }
    var d = f.ROMET("embed");
    for (var c = 0; c < d.length; c++) {
        try {
            d[c].style.display = d[c].OldStyle
        } catch(b) {}
    }
    var a = f.ROMET("iframe");
    for (var c = 0; a && c < a.length; c++) {
        Dialog.hideAllFlash(a[c].contentWindow)
    }
};
Dialog.prototype.addParam = function(a, b) {
    this.DialogArguments[a] = b
};
Dialog.prototype.close = function() {
	$('select').css('visibility','visible');
    if (this.WindowFlag) {
        this.ParentWindow.ROMED = null;
        this.ParentWindow.ROMEDW = null;
        this.Window.opener = null;
        this.Window.close();
        this.Window = null
    } else {
        var pw = ROMEE.getTopLevelWindow();
        var win = window;
        var flag = false;
        while (win != win.parent) {
            if (win._DialogInstance) {
                flag = true;
                win._DialogInstance.DialogDiv.style.zIndex = 960;
                break
            }
            win = win.parent
        }
        if (this.AlertFlag) {
            ROMEE.hide(pw.ROME("_AlertBGDiv"))
        }
        if (!flag && !this.AlertFlag) {
            pw.eval('window._OpacityFunc = function(){var w = ROMEE.getTopLevelWindow();ROMEE.hide(w.ROME("_DialogBGDiv"));}');
            pw.Effect.opacity(pw.ROME("_DialogBGDiv"), 40, 0, 100, pw._OpacityFunc)
        }
        this.DialogDiv.outerHTML = "";
        pw.Dialog._Array.remove(this.ID)
    }
};
Dialog.prototype.addButton = function(f, a, d) {
    var b = "<input id='_Button_" + this.ID + "_" + f + "' type='button' value='" + a + "'> ";
    var c = ROMEE.getTopLevelWindow();
    c.ROME("_DialogButtons_" + this.ID).ROMET("input")[0].getParent("a").insertAdjacentHTML("beforeBegin", b);
    Effect.initCtrlStyle(c.ROME("_Button_" + this.ID + "_" + f));
    c.ROME("_Button_" + this.ID + "_" + f).onclick = d
};
Dialog.close = function(a) {
    window.Args._DialogInstance.close()
};
Dialog.getInstance = function(c) {
    var a = ROMEE.getTopLevelWindow();
    var b = a.ROME("_DialogFrame_" + c);
    if (!b) {
        return null
    }
    return b.DialogInstance
};
Dialog.AlertNo = 0;
Dialog.alert = function(b, c, l, f) {
    var m = ROMEE.getTopLevelWindow();
    var g = new Dialog("_DialogAlert" + Dialog.AlertNo++);
    g.ParentWindow = m;
    g.Width = l ? l: 300;
    g.Height = f ? f: 120;
    g.Title = "系统提示";
    g.URL = "javascript:void(0);";
    g.AlertFlag = true;
    g.CancelEvent = function() {
        g.close();
        try{
        	ROMEDW.$('select').css('visibility','visible');
        }catch(e){}

        if (c) {
            c()
        }
    };
    g.show();
    m.ROME("_AlertBGDiv").show();
    ROMEE.hide(m.ROME("_ButtonOK_" + g.ID));
    var j = m.ROME("_DialogFrame_" + g.ID).contentWindow;
    var k = j.document;
    k.open();
    k.write("<body oncontextmenu='return false;'></body>");
    var d = [];
    d.push("<table height='100%' border='0' align='center' cellpadding='10' cellspacing='0'>");
    d.push("<tr><td align='right'><img id='Icon' src='/" + Server.ContextPath + "/images/dialog/icon_alert.gif' width='34' height='34' align='absmiddle'></td>");
    d.push("<td align='left' id='Message' style='font-size:9pt'>" + b + "</td></tr></table>");
    var a = k.createElement("div");
    a.innerHTML = d.join("");
    k.body.appendChild(a);
    k.close();
    var f = Math.max(k.documentElement.scrollHeight, k.body.scrollHeight);
    var l = Math.max(k.documentElement.scrollWidth, k.body.scrollWidth);
    if (l > 300) {
        j.frameElement.width = l
    }
    if (f > 120) {
        j.frameElement.height = f
    }
    g.CancelButton.value = "确 定";
    m.ROME("_DialogButtons_" + g.ID).style.textAlign = "center"
};
Dialog.confirm = function(b, n, m, k, d) {
    var l = ROMEE.getTopLevelWindow();
    var f = new Dialog("_DialogAlert" + Dialog.AlertNo++);
    f.Width = k ? k: 300;
    f.Height = d ? d: 120;
    f.Title = "信息确认";
    f.URL = "javascript:void(0);";
    f.AlertFlag = true;
    f.CancelEvent = function() {
        if (m) {
            m()
        }
        f.close()
    };
    f.OKEvent = function() {
        if (n) {
            n()
        }
        f.close()
    };
    f.show();
    l.ROME("_AlertBGDiv").show();
    var g = l.ROME("_DialogFrame_" + f.ID).contentWindow;
    var j = g.document;
    j.open();
    j.write("<body oncontextmenu='return false;'></body>");
    var c = [];
    c.push("<table height='100%' border='0' align='center' cellpadding='10' cellspacing='0'>");
    c.push("<tr><td align='right'><img id='Icon' src='/" + Server.ContextPath + "/images/dialog/icon_query.gif' width='34' height='34' align='absmiddle'></td>");
    c.push("<td align='left' id='Message' style='font-size:9pt'>" + b + "</td></tr></table>");
    var a = j.createElement("div");
    a.innerHTML = c.join("");
    j.body.appendChild(a);
    j.close();
    l.ROME("_DialogButtons_" + f.ID).style.textAlign = "center"
};
var _DialogInstance = window.frameElement ? window.frameElement.DialogInstance: null;
Page.onDialogLoad = function() {
    if (_DialogInstance) {
        if (_DialogInstance.Title) {
            document.title = _DialogInstance.Title
        }
        window.Args = _DialogInstance.DialogArguments;
        _DialogInstance.Window = window;
        window.Parent = _DialogInstance.ParentWindow
    }
};
Page.onDialogLoad();
Page.onLoad(function() {
    var f = _DialogInstance;
    if (f) {
        try {
            f.ParentWindow.ROMED = f;
            f.ParentWindow.ROMEDW = f.Window;
            var a = false;
            if (!this.AlertFlag) {
                var c = f.ParentWindow;
                while (c != c.parent) {
                    if (c._DialogInstance) {
                        a = true;
                        break
                    }
                    c = c.parent
                }
                if (!a) {
                    Effect.opacity(ROMEE.getTopLevelWindow().ROME("_DialogBGDiv"), 0, 40, 400)
                }
            }
            if (f.AlertFlag) {
                ROMEE.show(ROMEE.getTopLevelWindow().ROME("_AlertBGDiv"))
            }
            if (f.ShowButtonRow && ROMEE.visible(f.CancelButton)) {
                f.CancelButton.focus()
            }
            if (f.onLoad) {
                f.onLoad()
            }
        } catch(b) {
            alert("DialogOnLoad:" + b.message + "\t(" + b.fileName + " " + b.lineNumber + ")")
        }
    }
},
4);
Dialog.onKeyUp = function(b) {
    if (b.keyCode == 27) {
        var a = ROMEE.getTopLevelWindow();
        if (a.Dialog._Array.length > 0) {
            Page.mousedown();
            Page.click();
            var c = a.Dialog.getInstance(a.Dialog._Array[a.Dialog._Array.length - 1]);
            c.CancelButton.onclick.apply(c.CancelButton, [])
        }
    }
};
Dialog.dragStart = function(a) {
    DragManager.doDrag(a, this.getParent("div"))
};
if (isIE) {
    document.attachEvent("onkeyup", Dialog.onKeyUp)
} else {
    document.addEventListener("keyup", Dialog.onKeyUp, false)
}
function Menu() {
    this.X = null;
    this.Y = null;
    this.Param = null;
    this.Items = [];
    this.Event = null;
    Menu.prototype.setEvent = function(a) {
        var b = getEventPosition(a);
        this.X = b.x;
        this.Y = b.y;
        this.Event = a
    };
    Menu.prototype.setPosition = function(a, b) {
        this.X = a;
        this.Y = b
    };
    Menu.prototype.setParam = function(a) {
        this.Param = a
    };
    Menu.prototype.addItem = function(d, c, b, a) {
        this.Items.push([d, c, b, a])
    };
    Menu.prototype.onClick = function(a) {
        if (!this.Items[a][3]) {
            var b = this.Items[a][1];
            Menu.close();
            b.apply(this, [this.Param])
        }
    };
    Menu.prototype.getHtml = function() {
        var b = [];
        b.push("<html><head><style>");
        b.push("body {margin: 0px;background: #FFFFFF;color: #444444}");
        b.push("td {font-size: 12px;}");
        b.push(".imgdisabled img{opacity: 0.5; filter: gray Alpha(Opacity=50);}");
        b.push("</style></head><body>");
        b.push("<table border='0' id='_ContextMenu_Table' cellspacing='1' cellpadding='0' oncontextmenu='TopWindow.stopEvent(event);' onselectstart='TopWindow.stopEvent(event);' style='-moz-user-select:none;border:1px solid #8F8F73;background:#FFFFFF;'><tr><td>");
        for (var a = 0; a < this.Items.length; a++) {
            if (this.Items[a][0] == "-") {
                b.push("<table width='100%' border='0' cellspacing='0' cellpadding='0' height='1' style='padding-top:0px;padding-bottom:0px;height:1px; font-size:0px;background:#B9B99D;'><tr><td style='background:#D6DFF7; width:24px;' width='24'></td><td style='background:#B9B99D; width:66px;'></td></tr></table>");
                continue
            }
            if (true == this.Items[a][3]) {
                b.push("<table width='100%' border='0' cellspacing='0' cellpadding='0' class='imgdisabled' style='font-size:12px'><tr>");
                if (this.Items[a][2]) {
                    b.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='" + Server.ContextPath + this.Items[a][2] + "' width='20' height='20' hspace='2' vspace='1'/></td>")
                } else {
                    b.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='" + Server.ContextPath + "Framework/Images/blank.gif' width='20' height='20' hspace='2' vspace='1'/></td>")
                }
                b.push("<td style='background:#F7F8FD; color:#999999; padding-left:10px;' nowrap='true'>" + this.Items[a][0] + "</td>");
                b.push("<td style='background:#F7F8FD; width:15px; text-align:center' width='15'>&nbsp;</td>")
            } else {
                b.push("<table width='100%' border='0' cellspacing='0' cellpadding='0' style='cursor:pointer;font-size:12px' onclick='TopWindow.Menu.getInstance().onClick(" + a + ")' onmouseout='TopWindow.Menu.onMouseOut(this)' onmouseover='TopWindow.Menu.onMouseOver(this)'><tr>");
                if (this.Items[a][2]) {
                    b.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='" + Server.ContextPath + this.Items[a][2] + "' width='20' height='20' hspace='2' vspace='1'/></td>")
                } else {
                    b.push("<td height='22' width='24' align='center' valign='middle' style='background:#DDF;'><img src='" + Server.ContextPath + "Framework/Images/blank.gif' width='20' height='20' hspace='2' vspace='1'/></td>")
                }
                b.push("<td style='background:#F7F8FD; padding-left:10px;cursor:pointer;' nowrap='true'>" + this.Items[a][0] + "</td>");
                b.push("<td style='background:#F7F8FD; width:15px; text-align:center' width='15'>&nbsp;</td>")
            }
            b.push("</tr></table>")
        }
        b.push("</td></tr></table>");
        b.push("<script>function ROME(ele){return document.getElementById(ele);}function init(){TopWindow.Menu.adjustSize();}<\/script></body></html>");
        return b.join("\n")
    };
    Menu.prototype.setPosition = function() {
        var j = ROMEE.getTopLevelWindow();
        var a = j.ROME("_DivContextMenu");
        ROMEE.show(a);
        var g = j.ROME("_ContextMenu_Frame").contentWindow;
        var l = g.ROME("_ContextMenu_Table").offsetHeight;
        var f = g.ROME("_ContextMenu_Table").offsetWidth;
        var c = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
        var k = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
        var d = 0;
        var b = 0;
        var h = ROMEE.computePosition(this.X, this.Y, this.X, this.Y, "all", f, l, j);
        d = h.x + c;
        b = h.y + k;
        j.ROME("_DivContextMenu").style.width = f + "px";
        j.ROME("_DivContextMenu").style.height = l + "px";
        d = d < 0 ? 0 : d;
        a.style.left = d + "px";
        a.style.top = b + "px"
    };
    Menu.prototype.show = function() {
        if (this.Items.length == 0) {
            return
        }
        var a = ROMEE.getTopLevelWindow();
        var b;
        if (!a.ROME("_DivContextMenu")) {
            b = a.document.createElement("div");
            b.id = "_DivContextMenu";
            b.style.position = "absolute";
            b.style.zIndex = "999";
            b.style.left = 0;
            b.style.top = 0;
            b.style.backgroundColor = "#890";
            b.innerHTML = "<iframe id='_ContextMenu_Frame' frameborder='0' scrolling='no' width='100'></iframe>";
            a.document.body.appendChild(b);
            var d = a.ROME("_ContextMenu_Frame").contentWindow;
            var c = d.document;
            c.open();
            c.write(this.getHtml());
            c.close();
            d.TopWindow = a;
            d.init()
        } else {
            b = a.ROME("_DivContextMenu");
            b.show();
            var f = a.ROME("_ContextMenu_Frame");
            f.show();
            var d = a.ROME("_ContextMenu_Frame").contentWindow;
            var c = d.document;
            c.open();
            c.write(this.getHtml());
            c.close();
            d.TopWindow = a;
            d.init()
        }
        b.onclick = stopEvent;
        b.Instance = this;
        this.setPosition()
    }
}
Menu.adjustSize = function() {
    var b = ROME("_ContextMenu_Frame").contentWindow;
    var a = ROMEE.getDimensions(b.ROME("_ContextMenu_Table"));
    b.frameElement.height = a.height;
    b.frameElement.width = a.width
};
Menu.close = function() {
    var a = ROMEE.getTopLevelWindow().ROME("_DivContextMenu");
    if (a) {
        a.hide()
    }
};
Menu.getInstance = function() {
    var a = ROMEE.getTopLevelWindow().ROME("_DivContextMenu");
    if (a) {
        return a.Instance
    }
};
Menu.onMouseOver = function(b) {
    var a = b.rows[0].cells;
    a[0].style.backgroundColor = "#466CA6";
    a[1].style.backgroundColor = "#7096FA";
    a[1].style.color = "#FFFFFF";
    a[2].style.backgroundColor = "#7096FA"
};
Menu.onMouseOut = function(b) {
    var a = b.rows[0].cells;
    a[0].style.backgroundColor = "#D6DFF7";
    a[1].style.backgroundColor = "#F7F8FD";
    a[1].style.color = "";
    a[2].style.backgroundColor = "#F7F8FD"
};
Page.onClick(Menu.close);
document.oncontextmenu = function(a) {};
function Progress(b, d, a, c) {
    this.TaskID = b;
    this.Title = d;
    if (c) {
        this.Height = c
    } else {
        this.Height = 150
    }
    if (a) {
        this.Width = a
    } else {
        this.Width = 400
    }
}
Progress.prototype.show = function() {
    var b = ROMEE.getTopLevelWindow();
    b.clearTimeout(b.Effect._opacityID);
    var f = new Dialog("DialogProgress" + this.TaskID);
    f.Width = this.Width;
    f.Height = this.Height;
    f.Title = this.Title;
    f.URL = "javascript:void(0);";
    var h = this.TaskID;
    f.OKEvent = function() {
        Progress.stop(h)
    };
    f.show();
    Effect.opacity(b.ROME("_DialogBGDiv"), 0, 40, 400);
    var d = b.ROME("_DialogFrame_" + f.ID).contentWindow;
    var c = d.document;
    c.open();
    c.write("<style>.progressBox{border:#ddd 1px solid}");
    c.write(".progressBg{ background:#ddd url(" + Server.ContextPath + "Framework/Images/progressBg.gif);}");
    c.write(".progressLight{font-size:5px; line-height:5px; color:#99bb00; background:#99bb00}");
    c.write(".progressShadow{font-size:5px; line-height:6px; color:#779911; background:#779911}");
    c.write("table,td{border-collapse: collapse; border-spacing: 0;}</style>");
    c.write("<body oncontextmenu='return false;'></body>");
    var a = [];
    a.push("<table width='100%' height='100%' border='0'><tr>");
    a.push("  <td align='center' valign='middle'>");
    a.push("	<div id='Info' style='text-align:left;width:100%;font-size:12px'>&nbsp;</div><br>");
    a.push("      <table width='100%' border='1' cellpadding='1' cellspacing='0' class='progressBox'>");
    a.push("      <tr><td class='progressBg'>");
    a.push("				<table width='1%' border='0' cellpadding='0' cellspacing='0' id='tableP'>");
    a.push("          <tr><td class='progressLight'>-</td></tr>");
    a.push("          <tr><td class='progressShadow'>-</td></tr>");
    a.push("        </table></td></tr></table></td></tr></table>");
    var g = c.createElement("div");
    g.innerHTML = a.join("");
    c.body.appendChild(g);
    c.close();
    f.ParentWindow.ROMED = f;
    f.ParentWindow.ROMEDW = d;
    f.OKButton.value = "取消当前任务";
    f.CancelButton.value = "关闭进度窗口";
    Progress.getInfo(this.TaskID)
};
Progress.getInfo = function(b) {
    var a = new DataCollection();
    a.add("TaskID", b);
    Server.sendRequest("com.zving.framework.messages.LongTimeTaskPage.getInfo", a,
    function(c) {
        if (c.Status == 0) {
            Dialog.alert(c.Message)
        } else {
            if (Dialog.getInstance("DialogProgress" + b) == null) {
                return
            }
            var f = c.get("CurrentInfo");
            if (f != null) {
                ROMEDW.document.getElementById("Info").innerHTML = f
            }
            if (c.get("CompleteFlag") == "1") {
                ROMEDW.document.getElementById("tableP").width = "100%";
                Dialog.getInstance("DialogProgress" + b).OKButton.hide()
            } else {
                var d = c.get("Percent");
                if (d == "0") {
                    d = "1"
                }
                ROMEDW.document.getElementById("tableP").width = d + "%";
                window.setTimeout(Progress.getInfo, 1000, b)
            }
        }
    })
};
Progress.stop = function(b) {
    var a = new DataCollection();
    a.add("TaskID", b);
    Server.sendRequest("com.zving.framework.messages.LongTimeTaskPage.stop", a,
    function(c) {
        function d() {
            Server.sendRequest("com.zving.framework.messages.LongTimeTaskPage.stop", a,
            function(f) {
                if (f.Status == 1) {
                    Dialog.alert("任务己取消");
                    Dialog.getInstance("DialogProgress" + b).close()
                } else {
                    window.setTimeout(d, 1000, b)
                }
            })
        }
        Dialog.getInstance("DialogProgress" + b).CancelButton.value = "正在取消任务...";
        Dialog.getInstance("DialogProgress" + b).CancelButton.disable();
        d()
    })
};
var Selector = {};
Selector.show = function(c) {
    var b = ROMEE.getTopLevelWindow();
    c = ROMEE.getParent("div", c);
    var a = c.ROMEA("listurl");
    b.SourceWindow = window;
    if (c.id != b.Selector.showingID || !b.Selector.showingID) {
        var h = b.ROME("_SelectorDiv_");
        if (!h) {
            h = b.document.createElement("div");
            h.id = "_SelectorDiv_";
            h.style.position = "absolute";
            h.style.left = 0;
            h.style.top = 0;
            h.style.width = 0;
            h.style.height = 0;
            b.ROMET("body")[0].appendChild(h);
            h = ROME(h)
        }
        ROME(h).show();
        if (a != null && a.length > 0) {
            if (!b.ROME("_SelectorFrame" + c.id) || b.ROME("_SelectorFrame" + c.id).SourcePathName != window.location.pathname) {
                if (a.indexOf(":") == -1) {
                    a = Server.ContextPath + a
                }
                h.innerHTML = "<iframe id='_SelectorFrame" + c.id + "' frameborder='0' width='100%' src='" + a + "'></iframe>";
                var g = b.ROME("_SelectorFrame" + c.id).contentWindow;
                b.ROME("_SelectorFrame" + c.id).SourcePathName = window.location.pathname;
                g._OnLoad = function() {
                    Selector.removeFrameMouseDownEvent(g);
                    g.SelectedText = c.textField.value;
                    g.SelectedValue = c.value;
                    g.SelectorID = c.id;
                    Selector.setListURLStyle(c.id)
                }
            } else {
                Selector.setListURLStyle(c.id);
                var g = b.ROME("_SelectorFrame" + c.id).contentWindow;
                g.SelectedText = c.textField.value;
                g.SelectedValue = c.value
            }
        } else {
            if (!b.ROME("_SelectorFrame")) {
                h.innerHTML = "<iframe id='_SelectorFrame' frameborder='0' width='100%' src='about:blank'></iframe>";
                var f = b.ROME("_SelectorFrame").contentWindow;
                var d = f.document;
                d.open();
                d.write("<style type='text/css'>* { box-sizing: border-box; -moz-box-sizing: border-box; -khtml-box-sizing: border-box; -webkit-box-sizing: border-box; }");
                d.write("html,body {scrollbar-arrow-color: #68a;scrollbar-3dlight-color: #acd;scrollbar-shadow-color: #9bc;scrollbar-face-color: #def;scrollbar-darkshadow-color: #def;scrollbar-highlight-color: #fff;scrollbar-track-color: #eee;}");
                d.write("body{margin: 0;padding: 0;color: #444;min-height:100%;height: auto;_height:100%;_overflow:auto;text-align: justify;text-justify: inter-ideograph;font: 12px/1.4 Tahoma, SimSun, Verdana, sans-serif;background: #ffffff;}");
                d.write("body,a,div,img{ margin: 0; padding: 0;box-sizing: border-box; -moz-box-sizing: border-box; -khtml-box-sizing: border-box; -webkit-box-sizing: border-box;}");
                d.write("div,p,span{font: 12px/1.4 Tahoma, SimSun, Verdana, sans-serif;word-break: break-all;};");
                d.write(".optgroup {position:absolute;z-index:666;left:0;top:0;color: #369;}");
                d.write(".optgroup div{padding:2px;overflow: auto;overflow-x: hidden;max-height:300px;color: #369;border: 1px solid #678;background: #f7fafc url(" + Server.ContextPath + "Platform/Images/textarea_bg.gif) repeat 0 2px;width:auto;z-index:888;}");
                d.write(".optgroup a{cursor:default;display:block;color: #369;white-space: nowrap;padding:1px 3px 2px 6px;_padding:0 3px 0 6px;height:18px;min-width:2em;text-decoration:none;}");
                d.write(".optgroup a:hover,.optgroup a.ahover{color: #cff;text-decoration:none;background:#49e url(" + Server.ContextPath + "Framework/Images/optionbg_over.gif) repeat-x center;}");
                d.write(".optgroup a.ahover{background-image:none;}</style>");
                d.write("<body onselectstart='return false;' style='margin: 0px;-moz-user-select:none;' oncontextmenu='return false;'></body>");
                d.close();
                Selector.setListStyle(c.id)
            } else {
                Selector.setListStyle(c.id)
            }
        }
    }
    Misc.lockScroll(window)
};
Selector.setListURLStyle = function(b) {
    var m = ROMEE.getTopLevelWindow();
    m.Selector.showingID = b;
    var a = m.ROME("_SelectorDiv_");
    var g = ROMEE.getDimensions(b);
    var j = ROMEE.getPositionEx(b);
    a.style.cssText = "position:absolute;display:inline-block;z-index:999;width:" + g.width + "px;left:" + j.x + "px;top:" + (j.y + g.height) + "px";
    a = Selector.getSourceDiv(b);
    var c = m.ROME("_SelectorFrame" + b);
    var k = c.contentWindow.document;
    var l = Math.max(k.documentElement.scrollWidth, k.body.scrollWidth);
    var f = Math.max(k.documentElement.scrollHeight, k.body.scrollHeight);
    var d = a.ROMEA("listwidth") ? parseInt(a.ROMEA("listwidth")) : 0;
    var n = a.ROMEA("listheight") ? parseInt(a.ROMEA("listheight")) : 0;
    if (d > 0) {
        l = d
    }
    c.width = l;
    if (n > 0 && n < f) {
        f = n
    }
    c.height = f;
    j = ROMEE.computePosition(j.x, j.y, j.x, j.y + g.height, "right", l, f, m);
    if (a._ScrollTop) {
        k.body.scrollTop = a._ScrollTop
    }
    a = m.ROME("_SelectorDiv_");
    a.style.left = j.x + "px";
    a.style.top = j.y + "px";
    c.style.border = "1px solid #678"
};
Selector.setListStyle = function(b) {
    var n = ROMEE.getTopLevelWindow();
    n.Selector.showingID = b;
    var g = ROME(b + "_list").outerHTML;
    var a = n.ROME("_SelectorDiv_");
    var f = ROMEE.getDimensions(b);
    var l = ROMEE.getPositionEx(b);
    a.style.cssText = "position:absolute; display:inline-block;z-index:999;width:" + f.width + "px;left:-1000px;top:-1000px";
    var c = n.ROME("_SelectorFrame");
    var m = c.contentWindow.document;
    c.contentWindow.TopWindow = n;
    g = g.replace(/Selector\./g, "TopWindow.Selector.");
    if (m.body.childNodes.length == 0) {
        var k = m.createElement("div");
        k.innerHTML = g;
        m.body.appendChild(k)
    } else {
        m.body.childNodes[0].innerHTML = g
    }
    var j = m.getElementById(b + "_list");
    j.style.display = "inline";
    j = m.getElementById(b + "_ul");
    var h = ROMEE.getDimensions(j);
    a = Selector.getSourceDiv(b);
    var d = a.ROMEA("listwidth") ? parseInt(a.ROMEA("listwidth")) : 0;
    var o = a.ROMEA("listheight") ? parseInt(a.ROMEA("listheight")) : 0;
    if (!d || (d > f.width)) {
        d = f.width
    }
    c.width = d;
    j.style.width = d + "px";
    if (!o || o > h.height) {
        o = h.height
    }
    j.style.height = o + "px";
    c.height = o;
    if (a.options.length > 0) {
        j.childNodes[a.selectedIndex].className = "ahover";
        if (a._ScrollTop) {
            j.scrollTop = a._ScrollTop
        }
    }
    l = ROMEE.computePosition(l.x, l.y, l.x, l.y + f.height, "right", d, o, n);
    a = n.ROME("_SelectorDiv_");
    a.style.left = l.x + 1 + "px";
    a.style.top = l.y + "px"
};
Selector.removeFrameMouseDownEvent = function(c) {
    var a = c.Page.mouseDownFunctions;
    if (a) {
        for (var b = 0; b < a.length; b++) {
            if (a[b] == c.Selector.close) {
                a.remove(a[b])
            }
        }
    }
};
Selector.onArrowMouseOver = function(a) {
    var b = a.parentElement;
    if (b.options.length > 0) {
        b.options[b.selectedIndex].className = ""
    }
    a.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/arow_over.gif)";
    b.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/zSelectBg_left_over.gif)"
};
Selector.onArrowMouseOut = function(a) {
    var b = a.parentElement;
    a.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/arow.gif)";
    b.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/zSelectBg_left.gif)"
};
Selector.getSourceDiv = function(c) {
    var b = null;
    var a = ROMEE.getTopLevelWindow();
    if (a.SourceWindow && a.SourceWindow.ROME) {
        b = a.SourceWindow.ROME(c)
    }
    if (!b) {
        b = ROME(c);
        if (!b || !b.InitFlag) {
            alert("发生致命错误，显示列表时未找到" + c + "对应的Selector元素!")
        }
    }
    return b
};
Selector.setSelectedIndex = function(f, b) {
    var a = f.value;
    var d = ROME(f.Items[b]);
    f.ItemClickFlag = true;
    if (isIE) {
        f.value = d.ROMEA("value")
    } else {
        f._value = d.ROMEA("value")
    }
    f.textField.value = d.innerText;
    f.ItemClickFlag = false;
    if (a != d.ROMEA("value")) {
        try {
            Selector.invokeOnChange(f)
        } catch(c) {
            alert("Selector.invokeOnChange():" + c.message)
        }
    }
};
Selector.onItemClick = function(k, h) {
    var c = k.parentNode.id;
    c = c.substring(0, c.lastIndexOf("_"));
    var j = ROMEE.getTopLevelWindow();
    var b = j.ROME("_SelectorDiv_");
    b = Selector.getSourceDiv(c);
    var a = b.value;
    b.textField.value = k.innerHTML;
    b.ItemClickFlag = true;
    if (isIE) {
        b.value = k.getAttribute("value")
    } else {
        b._value = k.getAttribute("value")
    }
    var g = b.childNodes[2].childNodes[0].childNodes;
    for (var d = 0; d < g.length; d++) {
        g[d].className = "";
        if (ROME(g[d]).ROMEA("value") === k.getAttribute("value")) {
            b._selectedIndex = d;
            if (isIE) {
                b.selectedIndex = d
            }
        }
    }
    b.ItemClickFlag = false;
    if (j.ROME("_SelectorFrame")) {
        b._ScrollTop = k.parentNode.scrollTop
    }
    if (a != k.getAttribute("value")) {
        try {
            Selector.invokeOnChange(b)
        } catch(f) {
            alert("Selector.invokeOnChange():" + f.message)
        }
    }
    Selector.close()
};
Selector.close = function() {
    var b = ROMEE.getTopLevelWindow();
    if (b.Selector && b.Selector.showingID) {
        ROMEE.hide(b.ROME("_SelectorDiv_"));
        if (b.SourceWindow) {
            var c = b.SourceWindow.ROME(b.Selector.showingID).textField;
            try {
                c.onblur.apply(c, [])
            } catch(a) {}
            Misc.unlockScroll(b.SourceWindow)
        }
        b.SourceWindow = null;
        b.Selector.showingID = false
    }
};
Selector.onItemMouseOver = function(a) {
    var c = a.parentNode.id;
    c = c.substring(0, c.lastIndexOf("_"));
    var b = Selector.getSourceDiv(c);
    a.parentNode.childNodes[b.selectedIndex].className = "";
    a.className = "liOver"
};
Selector.setInput = function(b, a) {
    if (!a || a == "false") {
        if (isIE) {
            b.textField.onselectstart = stopEvent;
            b.textField.onmousedown = stopEvent
        } else {
            b.textField.style.MozUserSelect = "none";
            b.textField.onmousedown = function(c) {
                c = getEvent(c);
                var d = ROMEE.getTopLevelWindow();
                var f = c.srcElement.parentElement;
                if (f.id == d.Selector.showingID && d.SourceWindow == window) {
                    return stopEvent(c)
                }
            }
        }
        b.textField.oncontextmenu = stopEvent
    } else {
        if (isIE) {
            b.textField.onselectstart = null;
            b.textField.onmousedown = null
        } else {
            b.textField.style.MozUserSelect = ""
        }
    }
    b.textField.onkeydown = Selector.onKeyDown
};
Selector.onDoubleClick = function(a) {
    a = getEvent(a);
    Selector.show(a.srcElement)
};
Selector.onKeyDown = function(b) {
    b = getEvent(b);
    var a = b.srcElement;
    var c = a.parentElement;
    if (b.keyCode == 37 || b.keyCode == 38) {
        if (c.selectedIndex > 0) {
            c.selectedIndex = c.selectedIndex - 1
        }
    } else {
        if (b.keyCode == 39 || b.keyCode == 40) {
            if (c.selectedIndex < c.length - 1) {
                c.selectedIndex = c.selectedIndex + 1
            }
        }
    }
    if (b.keyCode != 9) {
        stopEvent(b)
    }
};
Selector.invokeOnChange = function(_ele) {
    if (!_ele.InitFlag) {
        var _cv = _ele.getAttribute("onChange");
        if (_cv) {
            var pw = ROMEE.getTopLevelWindow();
            if (pw.SourceWindow) {
                pw.SourceWindow.eval(_cv)
            } else {
                eval(_cv)
            }
        }
    }
};
Selector.setValueEx = function(c, a, b) {
    c = ROME(c);
    c.value = a;
    c.textField.value = b
};
Selector.setValue = function(d, b) {
    if (d.ROMEA("listURL")) {
        d.textField.value = b;
        d._value = b
    } else {
        var a = false;
        for (var c = 0; c < d.Items.length; c++) {
            if (ROME(d.Items[c]).ROMEA("value") == b) {
                d.textField.value = d.Items[c].innerText;
                d._value = b;
                if (isGecko) {
                    d._selectedIndex = c
                } else {
                    d.ItemClickFlag = true;
                    d.selectedIndex = c;
                    d.ItemClickFlag = false
                }
                a = true;
                break
            }
        }
        if (!a && d.input) {
            d.textField.value = b;
            d._value = b
        }
    }
    Selector.invokeOnChange(d)
};
Selector.setDisabled = function(b, a) {
    if (a || a == "true") {
        b.textField.disabled = true;
        b.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/zSelectBg_left_disab.gif)";
        b.arrow.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/arow_disab.gif)";
        b.arrow.onmouseover = stopEvent;
        b.arrow.onmouseout = stopEvent;
        b.arrow.onclick = stopEvent;
        b.arrow.onmousedown = stopEvent;
        b.textField.style.color = "#aaa";
        b.textField.ondblclick = stopEvent;
        b.textField.onkeydown = stopEvent
    } else {
        b.textField.disabled = false;
        b.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/zSelectBg_left.gif)";
        b.arrow.style.backgroundImage = "url(" + Server.ContextPath + "Framework/Images/arow.gif)";
        b.textField.ondblclick = Selector.onDoubleClick;
        b.arrow.onmouseover = function() {
            Selector.onArrowMouseOver(this)
        };
        b.arrow.onmouseout = function() {
            Selector.onArrowMouseOut(this)
        };
        b.arrow.onmousedown = function(c) {
            var d = ROMEE.getTopLevelWindow();
            var f = ROMEE.getParent("div", this);
            if (d.Selector && d.Selector.showingID == f.id) {
                return
            }
            Selector.show(this);
            this.parentElement.textField.onfocus.apply(this.parentElement.textField, arguments);
            stopEvent(c)
        };
        b.textField.style.color = "";
        b.textField.onkeydown = Selector.onKeyDown
    }
};
Selector.initHtml = function(s) {
    var l = [];
    var d = s.id;
    var c = s.ROMEA("name");
    if (!c) {
        c = d
    } else {
        s.removeAttribute("name")
    }
    var q = s.ROMEA("value");
    var o = s.ROMEA("verify");
    var f = s.ROMEA("condition");
    var j = "";
    if (o) {
        j = 'verify="' + o + '"';
        if (f) {
            j += ' condition="' + f + '"'
        }
    }
    var h = s.ROMEA("zclass");
    if (h) {
        h = 'class="' + h + '"'
    } else {
        h = ""
    }
    var b = "";
    if (s.ROMEA("styleOriginal") && s.ROMEA("styleOriginal") != "NULL") {
        b = s.ROMEA("styleOriginal")
    } else {
        var n = s.style.cssText;
        var a = /(.*width: *)([0-9]+)(px *;*.*)/gi;
        if (a.test(n)) {
            n = n.replace(a,
            function(t, w, v, u) {
                return v
            });
            b = "width:" + n + "px;"
        }
    }
    s.style.cssText = "display:inline-block; *zoom: 1;*display: inline;vertical-align:middle;position:relative;height:20px;white-space: nowrap;padding:0 0 0 5px;margin-right:3px;background:url(" + Server.ContextPath + "Framework/Images/zSelectBg_left.gif) no-repeat 0 0;";
    l.push("<input type='text' id='" + d + "_textField' ztype='select' " + j + " " + h + " name='" + c + "' autocomplete='off' style='height:18px; line-height:18px; _line-height:12px; padding:0;padding-top:2px;vertical-align:middle;border:0 none; background:transparent none; cursor:default;" + (isIE8 ? "line-height:18px;": "") + b + "' value=''/>");
    l.push("<img class='arrowimg' src='" + Server.ContextPath + "Framework/Images/blank.gif' width='18' height='20' id='" + d + "_arrow' style='position:relative; left:-17px; _top:-2px;margin-right:-18px; cursor:pointer; width:18px; height:20px;vertical-align:middle; background:url(" + Server.ContextPath + "Framework/Images/arow.gif) no-repeat 0 0;" + (isIE8 ? "top:0;": "") + "'/>");
    l.push("<div id='" + d + "_list' class='optgroup' style='text-align:left;display:none;'>");
    l.push("<div id='" + d + "_ul' style='left:-1px; width:-1px;'>");
    var m = s.ROMET("span");
    var g = 0;
    var r = true;
    for (var k = 0; k < m.length; k++) {
        ROME(m[k]);
        if (r && m[k].ROMEA("value") == q) {
            g = k;
            r = false
        }
        if (r && m[k].ROMEA("selected")) {
            g = k
        }
        l.push('<a href="javascript:void(0);" onclick="Selector.onItemClick(this);" onmouseover=\'Selector.onItemMouseOver(this)\' hidefocus value="' + m[k].ROMEA("value") + '">' + m[k].innerHTML + "</a>")
    }
    l.push("</div>");
    l.push("</div>");
    s.innerHTML = l.join("");
    s.InitFlag = true;
    if (isGecko) {
        s._selectedIndex = g
    } else {
        s.selectedIndex = g
    }
};
Selector.initMethod = function(b) {
    b.textField = b.childNodes[0];
    b.name = b.textField.name;
    b.type = "select-one";
    b.arrow = b.childNodes[1];
    b.Items = ROME(b.id + "_ul").childNodes;
    if (isIE) {
        b.value = b.Items.length > 0 ? ROME(b.Items[b.selectedIndex]).ROMEA("value") : null
    } else {
        b._value = b.Items.length > 0 ? ROME(b.Items[b._selectedIndex]).ROMEA("value") : null
    }
    b.form = b.getParent("form");
    b.options = b.Items;
    b.length = b.options.length;
    b.textField.onfocus = function() {
        Selector.show(this)
    };
    b.clear = function() {
        this.value = "";
        this.childNodes[2].childNodes[0].innerHTML = "";
        this.Items = this.childNodes[2].childNodes[0].childNodes;
        this.value = "";
        this.textField.value = ""
    };
    b.add = function(g, f, c) {
        var d = '<a href="javascript:void(0);" onclick="Selector.onItemClick(this);" hidefocus value="' + f + '">' + (g == "" ? "&nbsp;": g) + "</a>";
        if (!this.Items || this.Items.length == 0) {
            this.childNodes[2].childNodes[0].innerHTML = d;
            this.Items = this.childNodes[2].childNodes[0].childNodes;
            return
        }
        var h = this.Items.length - 1;
        if (c != null) {
            c = parseInt(c);
            if (c > h) {
                c = h
            }
        } else {
            c = h
        }
        this.Items[c].insertAdjacentHTML("afterEnd", d);
        this.Items = this.childNodes[2].childNodes[0].childNodes;
        if (this.Items.length > 10) {
            this.childNodes[2].childNodes[0].style.height = "15em"
        }
    };
    var a = b.ROMEA("disabled");
    b.disabled = false;
    if (isIE) {
        Selector.initMethodIE(b)
    } else {
        Selector.initMethodGecko(b)
    }
    b.selectedIndex = b.selectedIndex;
    b.input = b.ROMEA("input") || b.ROMEA("input") == "true" ? true: false;
    b.disabled = a;
    Selector.setDisabled(b, b.disabled);
    Selector.setInput(b, b.input);
    b.InitFlag = false
};
Selector.initMethodIE = function(a) {
    a.onpropertychange = function() {
        var c = event.srcElement;
        var b = c[event.propertyName];
        switch (event.propertyName.toLowerCase()) {
        case "disabled":
            Selector.setDisabled(c, b);
            break;
        case "selectedindex":
            if (!c.ItemClickFlag) {
                if (c.Items.length > 0) {
                    Selector.setSelectedIndex(c, c.selectedIndex)
                }
            }
            break;
        case "input":
            Selector.setInput(c, c.input);
            break;
        case "size":
            break;
        case "value":
            if (!c.ItemClickFlag) {
                Selector.setValue(c, b)
            }
            break
        }
    }
};
Selector.initMethodGecko = function(a) {
    a.__defineGetter__("disabled",
    function(b) {
        return this.textField.disabled
    });
    a.__defineSetter__("disabled",
    function(b) {
        Selector.setDisabled(this, b)
    });
    a.__defineGetter__("selectedIndex",
    function() {
        return this._selectedIndex
    });
    a.__defineSetter__("selectedIndex",
    function(b) {
        b = parseInt(b);
        if (b >= 0 && b < this.Items.length) {
            this._selectedIndex = b

        } else {
            return
        }
        Selector.setSelectedIndex(this, this._selectedIndex)
    });
    a.__defineGetter__("input",
    function() {
        return this._input
    });
    a.__defineSetter__("input",
    function(b) {
        this._input = b && b.toLowerCase() == "true";
        Selector.setInput(this, this._input)
    });
    a.__defineGetter__("size",
    function() {
        return this._size
    });
    a.__defineSetter__("size",
    function(b) {
        this._Size = b
    });
    a.__defineGetter__("value",
    function() {
        return this._value
    });
    a.__defineSetter__("value",
    function(b) {
        Selector.setValue(this, b)
    })
};
Selector.initCtrl = function(a) {
    a = ROME(a);
    Selector.initHtml(a);
    Selector.initMethod(a)
};
Selector.setReturn = function(d, a) {
    var h = window.SelectorID;
    var f = ROMEE.getTopLevelWindow();
    var g = Selector.getSourceDiv(h);
    var b = g.value;
    g.textField.value = d;
    if (isIE) {
        g.ItemClickFlag = true;
        g.value = a;
        g.ItemClickFlag = false
    } else {
        g._value = a
    }
    g._ScrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
    if (b != a) {
        try {
            Selector.invokeOnChange(g)
        } catch(c) {
            alert("Selector.invokeOnChange():" + c.message)
        }
    }
    if (f.Selector.showingID) {
        ROMEE.hide(f.ROME("_SelectorDiv_"));
        f.Selector.showingID = 0
    }
};
Selector.CtrlArray = [];
Selector.initHtmlAll = function() {
    var b = ROMET("div");
    var a = b.length;
    for (var c = a; c > 0; c--) {
        var f = ROME(b[c - 1]);
        var d = f.ROMEA("ztype");
        if (d && d.toLowerCase() == "select") {
            var g = f.id;
            Selector.initHtml(f);
            Selector.CtrlArray.push(g)
        }
    }
};
Selector.initMethodAll = function() {
    var a = Selector.CtrlArray.length;
    for (var b = 0; b < a; b++) {
        var c = ROME(Selector.CtrlArray[b]);
        Selector.initMethod(c);
        if (c.Items.length > 10) {
            ROME(c.id + "_ul").style.height = "15em"
        }
    }
};
Page.onLoad(Selector.initHtmlAll, 0);
Page.onLoad(Selector.initMethodAll, 1);
Page.onMouseDown(Selector.close);
var Tab = {};
Tab.onTabMouseOver = function(a) {
    if (a.className == "divtab") {
        a.className = "divtabHover"
    }
};
Tab.onTabMouseOut = function(a) {
    if (a.className == "divtabHover") {
        a.className = "divtab"
    }
};
Tab.onTabClick = function(f) {
    var b = ROMET("div", f.parentElement);
    var a = b.length;
    for (var d = 0; d < a; d++) {
        var h = b[d];
        var g = h.className;
        if (g == "divtabCurrent") {
            h.className = "divtab";
            h.style.zIndex = "" + (a - d) + ""
        }
    }
    f.className = "divtabCurrent";
    f.style.zIndex = "33"
};
Tab.initTab = function(d) {
    var a = ROMET("div", d.parentElement);
    var b = a.length;
    for (var c = 0; c < b; c++) {
        var f = a[c].className;
        if (f == "divtab" || f == "divtabDisabled") {
            a[c].style.zIndex = "" + (b - c) + ""
        } else {
            if (f == "divtabCurrent") {
                a[c].style.zIndex = "33"
            }
        }
    }
};
Tab.parentpadding = function(a) {
    if (a.parentElement.style.paddingLeft != "30px") {
        a.parentElement.style.paddingLeft = "30px"
    }
};
Tab.setDivtabWidth = function(f) {
    f = ROME(f);
    var a = 50;
    var j = 6;
    if (Application.isHMenu == true) {
        var h = 0;
        var d = f.ROMET("div");
        var g = d.length;
        var b = document.compatMode == "BackCompat" ? document.body.clientWidth: document.documentElement.clientWidth;
        for (var c = 0; c < g; c++) {
            h += d[c].clientWidth
        }
        if (h + 50 > b) {
            if (b - 120 - g * a > 0) {
                j = Math.ceil((b - 120 - g * a) / g)
            }
            for (var c = 0; c < g; c++) {
                ROMET("span", d[c])[0].style.cssText = "width:" + j + "px;"
            }
        } else {
            for (var c = 0; c < g; c++) {
                ROMET("span", d[c])[0].style.cssText = ""
            }
        }
    }
};
Tab.onChildTabMouseOver = function(a) {
    if (a.className == "divchildtab") {
        a.className = "divchildtabHover"
    }
};
Tab.onChildTabMouseOut = function(a) {
    if (a.className == "divchildtabHover") {
        a.className = "divchildtab"
    }
};
Tab.onChildTabClick = function(h) {
    h = ROME(h);
    var a = ROMET("div", h.parentElement);
    var g;
    for (var c = 0; c < a.length; c++) {
        var b = ROME(a[c]);
        var j = b.className;
        var d = ROME("_ChildTabFrame_" + b.ROMEA("id"));
        if (j == "divchildtabCurrent") {
            b.className = "divchildtab";
            g = d.getDimensions().height
        }
        d.style.height = 0
    }
    h.className = "divchildtabCurrent";
    var d = ROME("_ChildTabFrame_" + h.ROMEA("id"));
    d.style.width = "100%";
    d.style.height = g + "px"
};
Tab.initFrameHeight = function(l) {
    var g = ROME(l);
    var a = document.body;
    var c = document.compatMode == "BackCompat" ? document.body.clientHeight: document.documentElement.clientHeight;
    var k = g.getPosition();
    g.scrolling = "auto";
    g.height = c - g.getPosition().y;
    var h = g.offsetParent;
    while (h.offsetParent != document.body) {
        h = h.offsetParent
    }
    h = ROME(h);
    var j = h.getDimensions().height + h.getPosition().y - g.getDimensions().height - g.getPosition().y;
    g.height = g.height - j - (isIE ? 1 : 0)
};
Tab.getChildTab = function(a) {
    return ROME("_ChildTabFrame_" + a)
};
Tab.getCurrentTab = function() {
    var a = ROMET("div");
    var b = a.length;
    for (var c = 0; c < b; c++) {
        var d = a[c].className;
        if (d == "divchildtabCurrent") {
            return ROME("_ChildTabFrame_" + a[c].id)
        }
    }
};
Tab.disableTab = function(a) {
    a = ROME(a);
    a.className = "divchildtabDisabled";
    a.onclick2 = a.onclick;
    a.onclick = null
};
Tab.enableTab = function(a) {
    a = ROME(a);
    a.className = "divchildtab";
    if (a.onclick2) {
        a.onclick = a.onclick2;
        a.onclick2 = null
    }
};
Page.onLoad(function() {
    var a = ROMET("div");
    var b = a.length;
    for (var c = 0; c < b; c++) {
        var d = a[c].className;
        if (d == "divtab" || d == "divtabDisabled") {
            a[c].style.zIndex = "" + (b - c) + ""
        } else {
            if (d == "divtabCurrent") {
                a[c].style.zIndex = "33"
            }
        }
    }
});
var _TipIDCounter = 0;
function Tip(a, b) {
    this.Element = ROME(a);
    this.Message = b;
    this.AutoClose = false;
    this.Clock = 9;
    this.initHtml()
}
Tip.AutoCloseTips = [];
Tip.prototype.initHtml = function() {
    var a = [];
    a.push("  <table border='0' cellspacing='0' cellpadding='0' class='tooltiptable'>");
    a.push("  	<tr><td class='corner topleft'> </td><td class='topcenter'> </td>");
    a.push("  			<td class='corner topright'> </td></tr><tr><td class='bodyleft'> </td>");
    a.push("				<td class='tooltipcontent'>" + this.Message + "</td>");
    a.push("				<td class='bodyright'> </td></tr>");
    a.push("		<tr><td class='corner footerleft'> </td><td class='footercenter'> </td>");
    a.push("				<td class='corner footerright'> </td></tr></table>");
    a.push("		<div class='tooltipfang'></div>");
    this.Html = a.join("")
};
Tip.prototype.show = function() {
    var k = document.createElement("div");
    k.id = "" + _TipIDCounter++;
    k.style.position = "absolute";
    k.style.left = "0px";
    k.style.top = "0px";
    k.className = "tooltip callout" + this.Clock;
    ROMET("body")[0].appendChild(k);
    k = ROME(k);
    k.innerHTML = this.Html;
    var j = this.Element.getPosition();
    var f = this.Element.getDimensions();
    if (this.Element.ROMEA("ztype") && this.Element.ROMEA("ztype").toLowerCase() == "date") {
        var b = ROME(this.Element.nextSibling).getDimensions();
        f.width += b.width
    }
    var d = k.getDimensions();
    var h = this.Clock;
    var a, g;
    if (h == 2 || h == 3 || h == 4) {
        a = j.x - f.width
    }
    if (h == 8 || h == 9 || h == 10) {
        a = j.x + f.width
    }
    if (h == 11 || h == 12 || h == 1) {
        g = j.y + f.height
    }
    if (h == 5 || h == 6 || h == 7) {
        g = j.y - f.height
    }
    if (h == 9 || h == 3) {
        g = j.y + f.height / 2 - d.height / 2
    }
    k.style.left = a + "px";
    k.style.top = g + "px";
    k.style.zIndex = 850;
    ROME(k).show();
    this.Div = k;
    if (this.AutoClose) {
        Tip.AutoCloseTips.push(this)
    }
};
Tip.prototype.close = function() {
    if (this.Div) {
        this.Div.outerHTML = "";
        this.Div = null
    }
};
Tip.show = function(d, f, a, b) {
    d = ROME(d);
    var c = new Tip(d, f);
    c.AutoClose = a;
    if (b) {
        c.Clock = b
    }
    c.show();
    if (!c.AutoClose) {
        if (!d._Tips) {
            d._Tips = []
        }
        d._Tips.push(c)
    }
    return c
};
Tip.getTipCount = function(a) {
    a = ROME(a);
    if (!a._Tips) {
        return 0
    }
    return a._Tips.length
};
Tip.close = function(b) {
    b = ROME(b);
    if (b._Tips) {
        for (var a = 0; a < b._Tips.length; a++) {
            if (b._Tips[a]) {
                b._Tips[a].close()
            }
        }
        b._Tips = []
    }
};
Page.onMouseDown(function() {
    var a = Tip.AutoCloseTips;
    for (var b = a.length; b > 0; b--) {
        a[b - 1].close();
        a.splice(b - 1, 1)
    }
});
var Tree = {};
Constant.TreeLevel = "_ZVING_TREE_LEVEL";
Constant.TreeStyle = "_ZVING_TREE_STYLE";
Constant.Icon_Branch_NotLast_NotExpand = "Icons/treeicon01.gif";
Constant.Icon_Branch_NotLast_Expand = "Icons/treeicon02.gif";
Constant.Icon_Branch_Last_NotExpand = "Icons/treeicon04.gif";
Constant.Icon_Branch_Last_Expand = "Icons/treeicon05.gif";
Constant.Branch_NotLast_NotExpand = "1";
Constant.Branch_NotLast_Expand = "2";
Constant.Branch_Last_NotExpand = "3";
Constant.Branch_Last_Expand = "4";
Tree.CurrentItem = null;
Tree.onItemClick = function(a, b) {
    if (Tree.CurrentItem) {
        Tree.CurrentItem.className = ""
    }
    Tree.CurrentItem = b;
    b.className = "cur"
};
Tree.onItemDblClick = function(b, f) {
    b = getEvent(b);
    Tree.CurrentItem = f;
    if (Tree.hasChild(f) && !Tree.isRoot(f)) {
        var c;
        var a = f.ROMET("img");
        for (var d = 0; d < a.length; d++) {
            var g = a[d];
            if (g.src.indexOf("Icons/treeicon01") > 0 || g.src.indexOf("Icons/treeicon02") > 0 || g.src.indexOf("Icons/treeicon04") > 0 || g.src.indexOf("Icons/treeicon05") > 0) {
                c = g;
                break
            }
        }
        Tree.onBranchIconClick(b, c)
    }
};
Tree.onContextMenu = function(a) {
    a = getEvent(a);
    Tree.CurrentItem = a.srcElement
};
Tree.onMouseOver = function(b) {
    b = getEvent(b);
    var c = b.srcElement;
    if (c == Tree.CurrentItem) {
        return
    }
    var a = c.getAttribute("ztype");
    if (a && a.toLowerCase() == "rootmenu") {
        return
    } else {
        c.className = "over"
    }
};
Tree.onMouseOut = function(b) {
    b = getEvent(b);
    var c = b.srcElement;
    if (c == Tree.CurrentItem) {
        return
    }
    var a = c.getAttribute("ztype");
    if (a && a.toLowerCase() == "rootmenu") {
        return
    } else {
        c.className = ""
    }
};
Tree.isRoot = function(a) {
    return ROME(a).ROMEA("level") === "0"
};
Tree.hasChild = function(a) {
    if (a.getAttribute("lazy") == "1") {
        return true
    }
    if (a.nextSibling && a.nextSibling.tagName.toLowerCase() == "div") {
        return true
    }
    return false
};
Tree.onBranchIconClick = function(a, f) {
    a = getEvent(a);
    if (!f) {

        f = a.srcElement
    }
    var b = f;
    f = ROME(f).getParent("p");
    var c = f.getAttribute("lazy");
    if (c == "1") {
        Tree.lazyLoad(f,
        function() {
            f.setAttribute("lazy", "0")
        })
    } else {
        if (f.nextSibling && Tree.hasChild(f)) {
            ROMEE.toggle(f.nextSibling)
        }
    }
    var d = f.getAttribute("expand");
    var g;
    if (d == Constant.Branch_NotLast_NotExpand) {
        g = Constant.Icon_Branch_NotLast_Expand;
        d = Constant.Branch_NotLast_Expand
    } else {
        if (d == Constant.Branch_NotLast_Expand) {
            g = Constant.Icon_Branch_NotLast_NotExpand;
            d = Constant.Branch_NotLast_NotExpand
        } else {
            if (d == Constant.Branch_Last_Expand) {
                g = Constant.Icon_Branch_Last_NotExpand;
                d = Constant.Branch_Last_NotExpand
            } else {
                if (d == Constant.Branch_Last_NotExpand) {
                    g = Constant.Icon_Branch_Last_Expand;
                    d = Constant.Branch_Last_Expand
                }
            }
        }
    }
    f.setAttribute("expand", d);
    b.src = Server.ContextPath + g;
    stopEvent(a)
};
Tree.init = function(a) {
    a = ROME(a);
    Tree.setParam(a, Constant.ID, a.id);
    Tree.setParam(a, Constant.Method, a.getAttribute("method"));
    Tree.setParam(a, Constant.TagBody, a.TagBody)
};
Tree.getParam = function(b, a) {
    b = ROME(b);
    return b.Params.get(a)
};
Tree.setParam = function(c, a, b) {
    c = ROME(c);
    if (!c.Params) {
        c.Params = new DataCollection()
    }
    c.Params.add(a, b)
};
Tree.loadData = function(b, a) {
    b = ROME(b);
    var c = b.id;
    Server.sendRequest("com.zving.framework.controls.TreePage.doWork", b.Params,
    function(g) {
        var f = document.createElement("div");
        f.setAttribute("ztype", "_Tree");
        f.innerHTML = g.get("HTML");
        var d = ROMEE.getParentByAttr(b, "ztype", "_Tree");
        if (d) {
            b = d
        }
        b.parentElement.replaceChild(f, b);
        if (isIE) {
            execScript(f.getElementsByTagName("script")[0].text)
        }
        b = null;
        Tree.CurrentItem = null;
        if (a) {
            a()
        }
    })
};
Tree.lazyLoad = function(c, b) {
    c = ROME(c);
    var a = c.getParentByAttr("ztype", "_Tree");
    Tree.setParam(a, "ParentLevel", c.getAttribute("level"));
    Tree.setParam(a, "ParentID", c.getAttribute("cid"));
    Tree.setParam(a, "LevelStr", c.getAttribute("levelstr"));
    Server.sendRequest("com.zving.framework.controls.TreePage.doWork", a.Params,
    function(f) {
        var d = document.createElement("div");
        d.innerHTML = f.get("HTML");
        c.insertAdjacentElement("afterEnd", d);
        Tree.setParam(a, "ParentLevel", "");
        Tree.setParam(a, "ParentID", "");
        Tree.setParam(a, "LevelStr", "");
        if (b) {
            b()
        }
    })
};
Tree.select = function(c, b, d, g) {
    c = ROME(c);
    var a = c.getElementsByTagName("p");
    for (var f = 0; f < a.length; f++) {
        var h = a[f];
        if (h.getAttribute(b) == d) {
            h.className = "cur";
            Tree.CurrentItem = h;
            if (g) {
                h.onclick.apply(h)
            }
            break
        }
    }
};
Tree.clear = function(a) {
    a = ROME(a);
    a.innerHTML = "";
    Tree.CurrentItem = null
};
Tree.dragEnd = function(evt) {
    var afterDrag = ROME(this).ROMEA("afterDrag");
    if (!afterDrag) {
        return
    }
    var func = eval(afterDrag);
    func.apply(this, arguments)
};
Tree.dragOver = function(a) {
    this.style.fontWeight = "bold";
    this.style.backgroundColor = "#EDFBD2"
};
Tree.dragOut = function(a) {
    this.style.fontWeight = "normal";
    this.style.backgroundColor = "#FFF"
};