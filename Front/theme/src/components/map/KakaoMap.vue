<template>
    <div>
        <div id="map" class="map"></div>
    </div>
</template>

<script>

export default {

    data() {
        return {
            map : {}
        }
    },
    computed: {
        // isList() {
        //     return _.isEmpty(this.ranklist)
        // }
    },

    mounted() {
        window.kakao && window.kakao.maps
            ? this.initMap()
            : this.addKakaoMapScript();
    },
    methods: {
        addKakaoMapScript() {
            const script = document.createElement("script");
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src =
                "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7a491241bbbfebc9a44447884a9c394e";
            document.head.appendChild(script);
        },
        initMap() {
            let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
            let options = {
                //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
                level: 3 //지도의 레벨(확대, 축소 정도)
            };

            // var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
            this.map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
            this.map.setCenter(new kakao.maps.LatLng(37.537183, 127.005454));
        },
        getCenter() {
            let position = this.map.getCenter();
            console.log(position)
        },
        // bounds에 속한 좌표를 한번에 보여주게 지도 조정
        setBound(bounds) {
            this.map.setBounds(bounds);
        },
        getBound() {
            let bounds = this.map.getBounds();
            console.log(bounds)
        },
        addMarker() {
            let marker = new kakao.maps.Marker({
                map: this.map,
                position: new kakao.maps.LatLng(33.450701, 126.570667)
            });
            console.log(marker)
        }

    }
};
</script>

<style>
.map {
    width: 100%;
    height: 400px;
}
</style>