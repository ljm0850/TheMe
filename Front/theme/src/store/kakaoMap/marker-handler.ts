const kakapo = window
class MarkerHandler {
    vueKakaoMap: any;
    constructor(vueKakaoMap:any) {
        console.log("[vue component]", vueKakaoMap)
        this.vueKakaoMap = vueKakaoMap;
    }
    // add(userData:any, fnConv:any) {
    //     userData.forEach((data: any) => {
    //         const option = fnConv(data)
    //     })
    // }
    
}

export default MarkerHandler;