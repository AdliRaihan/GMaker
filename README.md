# GMaker


    fun CheckConnectivity(Cntx:Context){
        val TAGS_TEST = "TEST RUN"
        (Cntx.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
                .activeNetworkInfo.apply {
            Toast.makeText(Cntx,StringBuilder().also {
                if((this != null)&&isConnected){
                    toString().let {
                        log -> Log.i(TAGS_TEST,log)
                        it.append("Connectivity")
                    }
                }else if(this == null){
                    toString().let {
                        log -> Log.i(TAGS_TEST,log)
                        it.append("No Connectivity")
                    }
                }
            }, Toast.LENGTH_SHORT).show()
        }
  
  </code>
