const mongoose = require("mongoose");
const UserSC = require("./modules/user");
const MessageSC = require("./modules/messages");



const uri = "mongodb+srv://" + process.env.DB_USER + ":" + process.env.DB_PASS
    + "@android-chat-db-shop-ekenn.gcp.mongodb.net/message?retryWrites=true&w=majority";

mongoose.connect(uri,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true,
        useFindAndModify: true
    },
    (error) => {
        if (error) handleError(error);
        else {
            console.log("connect successful")
        }
    }
);

//const db = mongoose.connection;

let db = {}
let Model = mongoose.model('messages', UserSC);



db.findUsers = async () => {
    let temp  = await Model.find().lean().select('id_user').exec();
    return temp;
}

db.findMess = async (id) => {
    let temp = await Model.find({ 'id_user': id }).exec();
    return temp[0].get("mess", Array);
}

db.addMess = async (_id, _mess, _send) => {
    if(_id === 1){
        console.log("ID wrong");
        return;
    }
    console.log("Dang add trong utils");
    Model.exists({'id_user': _id}, async (err, res) => {
        if(!res) {
            // Tao ra SC moi de add tin nhan vao
            const user = new Model({id_user: _id, mess: [{detail: _mess, send: _send}]});
            //console.log(user);
            await user.save().then((_res)=>{
                if(_res) {
                    return true;
                    console.log("save:" + _res);
                }
            });
        } else {
            // Cap nhat tin nhan vao document (update)
            Model.findOneAndUpdate({id_user: _id}, {$push: {mess: {detail: _mess, send: _send}}}, (err, res) =>{
                if(err) {
                    handleError(err);
                    return false;
                }
                else {
                    return true;
                }
            });
        }
    })
}




module.exports = db;