import _ from "lodash"
const kakao = window.kakao
// var nowMarker = null 
class MarkerHandler {
    constructor(vueKakaoMap) {
        // console.log("[vue component]", vueKakaoMap)
        this.vueMap = vueKakaoMap;
    }
    add(userData) {
        if (_.isEmpty(userData)) {
            return
        }
        userData.forEach(data => {
            // if (nowMarker) {
            //     nowMarker.setMap(null)
            // }
            new kakao.maps.Marker({
                map: this.vueMap.mapInstance,
                position: new kakao.maps.LatLng(data.y,data.x)
            });
            // nowMarker.setMap(this.vueMap.mapInstance)
        });
    }
    
}

export default MarkerHandler;