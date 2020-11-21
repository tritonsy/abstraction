import Vue from 'vue'
import Vuetify from 'vuetify'
import 'api/resource'
import '@babel/polyfill'
import store from 'store/store'
import App from 'pages/App.vue'
import {connect} from './util/ws'
import 'vuetify/dist/vuetify.min.css'

const vuetifyOptions = { }
if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    store,
    vuetify: new Vuetify(vuetifyOptions),
    render: a => a(App)
})
