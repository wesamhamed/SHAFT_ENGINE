package com.shaft.tools.support;

public enum JSHelpers {
    LOAD_JQUERY("/** dynamically load jQuery */\n" + "(function(jqueryUrl, callback) {\n"
            + "    if (typeof jqueryUrl != 'string') {\n"
            + "        jqueryUrl = 'https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js';\n" + "    }\n"
            + "    if (typeof jQuery == 'undefined') {\n" + "        var script = document.createElement('script');\n"
            + "        var head = document.getElementsByTagName('head')[0];\n" + "        var done = false;\n"
            + "        script.onload = script.onreadystatechange = (function() {\n"
            + "            if (!done && (!this.readyState || this.readyState == 'loaded'\n"
            + "                    || this.readyState == 'complete')) {\n" + "                done = true;\n"
            + "                script.onload = script.onreadystatechange = null;\n"
            + "                head.removeChild(script);\n" + "                callback();\n" + "            }\n"
            + "        });\n" + "        script.src = jqueryUrl;\n" + "        head.appendChild(script);\n" + "    }\n"
            + "    else {\n" + "        callback();\n" + "    }\n"
            + "})(arguments[0], arguments[arguments.length - 1]);"),
    ELEMENT_DRAG_AND_DROP("(function( $ ) {\n" + "        $.fn.simulateDragDrop = function(options) {\n"
            + "                return this.each(function() {\n"
            + "                        new $.simulateDragDrop(this, options);\n" + "                });\n"
            + "        };\n" + "        $.simulateDragDrop = function(elem, options) {\n"
            + "                this.options = options;\n" + "                this.simulateEvent(elem, options);\n"
            + "        };\n" + "        $.extend($.simulateDragDrop.prototype, {\n"
            + "                simulateEvent: function(elem, options) {\n" + "\n"
            + "                        /*Simulating drag start*/\n"
            + "                        var type = 'dragstart';\n"
            + "                        var event = this.createEvent(type);\n"
            + "                        this.dispatchEvent(elem, type, event);\n" + "\n"
            + "			/*Simulating drag enter*/\n" + "                        type = 'dragenter';\n"
            + "                        var dragenterEvent1 = this.createEvent(type, {});\n"
            + "			dragenterEvent1.dataTransfer = event.dataTransfer;\n"
            + "                        this.dispatchEvent(elem, type, dragenterEvent1);\n" + "\n"
            + "			/*Simulating drag over*/\n" + "			type = 'dragover';\n"
            + "                        var dragoverEvent1 = this.createEvent(type, {});\n"
            + "                        dragoverEvent1.dataTransfer = event.dataTransfer;\n"
            + "                        this.dispatchEvent(elem, type, dragoverEvent1);\n" + "\n"
            + "			/*Simulating drag leave*/\n" + "                        type = 'dragleave';\n"
            + "                        var dragleaveevent = this.createEvent(type, {});\n"
            + "			dragleaveevent.dataTransfer = event.dataTransfer;\n"
            + "                        this.dispatchEvent(elem, type, dragleaveevent);\n" + "\n"
            + "			/*Sleep for 1000 milliseconds (1 second)*/\n"
            + "			var start = new Date().getTime();\n"
            + "			for (var i = 0; i < 1e7; i++) {\n"
            + "				if ((new Date().getTime() - start) > 1000){\n"
            + "				break;\n" + "				}\n" + "			}\n"
            + "\n" + "			/*Simulating drag enter*/\n" + "			type = 'dragenter';\n"
            + "                        var dragenterEvent = this.createEvent(type, {});\n"
            + "                        dragenterEvent.dataTransfer = event.dataTransfer;\n"
            + "                        this.dispatchEvent($(options.dropTarget)[0], type, dragenterEvent);\n" + "\n"
            + "			/*Simulating drag over*/\n" + "			type = 'dragover';\n"
            + "                        var dragoverEvent = this.createEvent(type, {});\n"
            + "                        dragoverEvent.dataTransfer = event.dataTransfer;\n"
            + "                        this.dispatchEvent($(options.dropTarget)[0], type, dragoverEvent);\n" + "\n"
            + "                        /*Simulating drop*/\n" + "                        type = 'drop';\n"
            + "                        var dropEvent = this.createEvent(type, {});\n"
            + "                        dropEvent.dataTransfer = event.dataTransfer;\n"
            + "                        this.dispatchEvent($(options.dropTarget)[0], type, dropEvent);\n" + "\n"
            + "                },\n" + "                createEvent: function(type) {\n"
            + "                        var event = document.createEvent(\"CustomEvent\");\n"
            + "                        event.initCustomEvent(type, true, true, null);\n"
            + "                        event.dataTransfer = {\n" + "                                data: {\n"
            + "                                },\n" + "                                setData: function(type, val){\n"
            + "                                        this.data[type] = val;\n"
            + "                                },\n" + "                                getData: function(type){\n"
            + "                                        return this.data[type];\n"
            + "                                }\n" + "                        };\n"
            + "                        return event;\n" + "                },\n"
            + "                dispatchEvent: function(elem, type, event) {\n"
            + "                        if(elem.dispatchEvent) {\n"
            + "                                elem.dispatchEvent(event);\n"
            + "                        }else if( elem.fireEvent ) {\n"
            + "                                elem.fireEvent(\"on\"+type, event);\n" + "                        }\n"
            + "                }\n" + "        });\n" + "})(jQuery);"),
    ELEMENT_GET_XPATH("function getXPath(element) {\n" + "    var xpath = '';\n" + "    var count = 0;\n"
            + "    while (element) {\n" + "\n" + "        /** Getting the Element's Index\n" + "         **/\n"
            + "        var pathIndex = \"\";\n" + "        if ($$GetIndex$$) {\n" + "            try {\n"
            + "                var index = 0;\n"
            + "                for (var sibling = element.previousSibling; sibling; sibling = sibling.previousSibling) {\n"
            + "                    if (sibling.nodeType == Node.DOCUMENT_TYPE_NODE)\n"
            + "                        continue;\n" + "\n"
            + "                    if (sibling.nodeName == element.nodeName)\n" + "                        ++index;\n"
            + "                }\n"
            + "                var pathIndex = (index ? \"[\" + (index + 1) + \"]\" : \"[1]\");\n"
            + "            } catch (err) {\n" + "                continue;\n" + "            }\n" + "        }\n" + "\n"
            + "        /** Getting the Element's Xpath\n" + "         **/\n" + "        var nodeXpath = '';\n" + "\n"
            + "        /** Try to get element ID\n" + "         **/\n" + "        try {\n"
            + "            if (element.id && $$GetId$$) {\n"
            + "                nodeXpath += '@id=\\\"' + element.id + '\\\"';\n" + "            }\n"
            + "        } catch (err) {}\n" + "\n" + "        /** Try to get element Name\n" + "         **/\n"
            + "        try {\n" + "            if (element.name && $$GetName$$) {\n"
            + "                if (nodeXpath != '')\n" + "                    nodeXpath += ' and ';\n"
            + "                nodeXpath += '@name=\\\"' + element.name + '\\\"';\n" + "            }\n"
            + "        } catch (err) {}\n" + "\n" + "        /** Try to get element Type\n" + "         **/\n"
            + "        try {\n"
            + "            if (element.hasAttribute(\"type\") && typeof element.type !== 'undefined' && $$GetType$$) {\n"
            + "                if (nodeXpath != '')\n" + "                    nodeXpath += ' and ';\n"
            + "                nodeXpath += '@type=\\\"' + element.type + '\\\"';\n" + "            }\n"
            + "        } catch (err) {}\n" + "\n" + "        /** Try to get element Class Name\n" + "         **/\n"
            + "        try {\n" + "            if (element.className && nodeXpath == '' && $$GetClass$$) {\n"
            + "                if (nodeXpath != '')\n" + "                    nodeXpath += ' and ';\n"
            + "                nodeXpath += '@class=\\\"' + element.className + '\\\"';\n" + "            }\n"
            + "        } catch (err) {}\n" + "\n" + "        /** Try to get element Text\n" + "         **/\n"
            + "        try {\n"
            + "            if (element.textContent && element.textContent.length < 50 && element.textContent == element.innerHTML && $$GetText$$) {\n"
            + "                var uiElementText = element.textContent;\n" + "                try {\n"
            + "                    uiElementText = uiElementText.trim();\n" + "                } catch (err) {\n"
            + "                    uiElementText = uiElementText.replace(/^\\s+|\\s+$/g, '');\n" + "                }\n"
            + "                if (nodeXpath != '')\n" + "                    nodeXpath += ' and ';\n"
            + "                if (element.textContent == uiElementText && element.textContent.length > 0)\n"
            + "                    nodeXpath += 'text()=\\\"' + element.textContent + '\\\"';\n"
            + "                else\n"
            + "                    nodeXpath += 'normalize-space() = \\\"' + uiElementText + '\\\"';\n"
            + "            } else if (element.text && element.text.length < 50 && element.text == element.innerHTML && $$GetText$$) {\n"
            + "                var uiElementText = element.text;\n" + "                try {\n"
            + "                    uiElementText = uiElementText.trim();\n" + "                } catch (err) {\n"
            + "                    uiElementText = uiElementText.replace(/^\\s+|\\s+$/g, '');\n" + "                }\n"
            + "                if (uiElementText.length > 0) {\n" + "                    if (nodeXpath != '')\n"
            + "                        nodeXpath += ' and ';\n"
            + "                    if (element.text == uiElementText)\n"
            + "                        nodeXpath += 'contains(text(),\\'' + uiElementText + '\\')';\n"
            + "                    else\n"
            + "                        nodeXpath += 'contains(normalize-space(),\\'' + uiElementText + '\\')';\n"
            + "                }\n"
            + "            } else if (element.innerText && element.innerText.length < 50 && element.innerText == element.innerHTML && $$GetText$$) {\n"
            + "\n" + "                var uiElementText = element.innerText;\n" + "                try {\n"
            + "                    uiElementText = uiElementText.trim();\n" + "                } catch (err) {\n"
            + "                    uiElementText = uiElementText.replace(/^\\s+|\\s+$/g, '');\n" + "                }\n"
            + "                if (uiElementText.length > 0) {\n" + "                    if (nodeXpath != '')\n"
            + "                        nodeXpath += ' and ';\n"
            + "                    if (element.innerText == uiElementText)\n"
            + "                        nodeXpath += 'contains(text(),\\'' + uiElementText + '\\')';\n"
            + "                    else\n"
            + "                        nodeXpath += 'contains(normalize-space(),\\'' + uiElementText + '\\')';\n"
            + "                }\n"
            + "            } else if (element.nodeName.toLocaleLowerCase() == \"a\" || count == 0) {\n"
            + "                var uiElementText = element.textContent;\n" + "                try {\n"
            + "                    uiElementText = uiElementText.trim().substring(0, 10);\n"
            + "                    uiElementText = uiElementText.replace(\"'\", \"') and contains (.,'\");\n"
            + "                } catch (err) {\n"
            + "                    uiElementText = uiElementText.replace(/^\\s+|\\s+$/g, '');\n"
            + "                    uiElementText = uiElementText.replace(\"'\", \"') and contains (.,'\");\n"
            + "                }\n" + "                if (uiElementText.length > 0) {\n"
            + "                    if (nodeXpath != '')\n" + "                        nodeXpath += ' and ';\n"
            + "                    if (element.textContent == uiElementText)\n"
            + "                        nodeXpath += 'contains(normalize-space(),\\'' + uiElementText + '\\')';\n"
            + "                    else\n"
            + "                        nodeXpath += 'contains(.,\\'' + uiElementText + '\\')';\n"
            + "                }\n" + "            }\n" + "        } catch (err) {}\n" + "\n"
            + "        /** Getting the Element's Tag Name\n" + "         **/\n"
            + "        var currentElementTagName = element.nodeName.toLocaleLowerCase();\n" + "\n"
            + "        /** Building Xpath for the current Element Node\n" + "         **/\n"
            + "        if (nodeXpath == '') {\n"
            + "            xpath = '/' + currentElementTagName + pathIndex + xpath;\n" + "        } else {\n"
            + "            xpath = '/' + currentElementTagName + pathIndex + '[' + nodeXpath + ']' + xpath;\n"
            + "        }\n" + "\n" + "        /** Switching focus to parent node\n" + "         **/\n"
            + "        element = element.parentElement;\n" + "\n"
            + "        /** Incrementing the element counter and breaking the loop in case we reach the maximum number of elements defined by the user\n"
            + "         **/\n" + "        count++;\n" + "        if (count >= $$MaxCount$$)\n" + "            break;\n"
            + "    }\n" + "\n" + "    return '/' + xpath;\n" + "};\n" + "\n"
            + "if (arguments[0] && !window.lastelem) {\n" + "    return getXPath(arguments[0]);\n"
            + "} else if (!arguments[0] && window.lastelem) {\n"
            + "    window.lastelem.style.outline = currentoutlineStyle;\n"
            + "    window.lastelem.style.backgroundColor = currentbackgroundColorStyle;\n"
            + "    return getXPath(window.lastelem);\n" + "}"),
    ELEMENT_SCROLL_TO_VIEWPORT(
            "(function(){'use strict';var api;api=function(x,y){var elm,scrollX,scrollY,newX,newY;scrollX=window.pageXOffset;scrollY=window.pageYOffset;window.scrollTo(x,y);newX=x-window.pageXOffset;newY=y-window.pageYOffset;elm=this.elementFromPoint(newX,newY);window.scrollTo(scrollX,scrollY);return elm;};this.document.elementFromAbsolutePoint=api;}).call(this);return document.elementFromAbsolutePoint(arguments[0], arguments[1]);"),
    WINDOW_FOCUS("window.focus();"), WINDOW_RESET_LOCATION("window.moveTo(0,0);"),
    WINDOW_RESIZE("window.resizeTo($WIDTH,$HEIGHT);"), NAVIGATION_STOP("return window.stop;"),
    DOCUMENT_READYSTATE("return document.readyState");

    private final String value;

    JSHelpers(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }
}
