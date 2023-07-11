// 1. 引入FastClick
import FastClick from 'fastclick'
// 2. 配置FastClick
if ('addEventListener' in document) {
    document.addEventListener('DOMContentLoaded', function () {
        FastClick.attach(document.body);
    }, false);
}
