
const router=require("express").Router();
const apiRouter=require("./apiRoutes");
router.use("/api",apiRouter);
router.use("/",(req,res)=>{res.json({"startingpage":"Server is running, please use /api for retrieving data"})})

module.exports=router;