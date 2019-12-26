const {Schema} = require('mongoose');

module.exports = new Schema({
    time: { type: Date, default: Date.now },
    detail: String,
    send: Boolean // admin send this message
})