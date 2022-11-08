const kakao = window.kakao

class MarkerHandler {
    constructor(vueKakaoMap) {
        // console.log("[vue component]", vueKakaoMap)
        this.vueMap = vueKakaoMap;
    }
    add(userData, fnConv) {
        userData.forEach((data) => {
            const option = fnConv(data)
            const markerInstance = new kakao.maps.Marker({
                map: this.vueMap.mapInstance,
                position: new kakao.maps.LatLng(option.lat,option.lng)
            });
            console.log(markerInstance)
        })
    }
    
}

export default MarkerHandler;