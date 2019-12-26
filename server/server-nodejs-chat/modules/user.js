const mongoose = require('mongoose');

module.exports = mongoose.Schema({
    id_user: Number,
    mess: [{
        time: { type: Date, default: Date.now },
        detail: String,
        send: Boolean
    }]
})