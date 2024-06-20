module.exports= {
    user:process.env.DB_DEV_USER,
    password:process.env.DB_DEV_PASSWORD,
    server:process.env.DB_DEV_URL,
    database:process.env.DB_DEV_DB,
    options:{
 
        trustServerCertificate: true,
  
    },
    port:1433
}