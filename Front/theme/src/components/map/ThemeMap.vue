<template>
    <div>
        <div id="map" class="map"></div>
    </div>
</template>

<script>

export default {

    data() {
        return {
            markers: [],
            infowindow: null,
        }
    },
    props : ['articleList'],

    computed: {
        sampleList: function () {
            return this.articleList
        },

    },
    watch: {
        sampleList() {
            this.displayMarker();
        },
    },


    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement("script");
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src =
                "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7a491241bbbfebc9a44447884a9c394e";
            document.head.appendChild(script);
        }
        this.displayMarker();
    },
    methods: {
        initMap() {
            let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
            let options = {
                //지도를 생성할 때 필요한 기본 옵션
                center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
                level: 2 //지도의 레벨(확대, 축소 정도)
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
        displayMarker() {
            if (this.markers.length > 0) {
                this.markers.forEach((marker) => marker.setMap(null));
            }

            const positions = [];
            for (let i = 0; i < this.articleList.length; i++) {
                console.log(this.articleList[i]);
                let position = new kakao.maps.LatLng(
                    this.articleList[i].latitude,
                    this.articleList[i].longitude
                );
                positions.push(position);
            }

            if (positions.length > 0) {
                this.markers = positions.map(
                    (position) =>
                    new kakao.maps.Marker({
                        map: this.map,
                        position,
                    })
            );

            const bounds = positions.reduce(
                (bounds, latlng) => bounds.extend(latlng),
                new kakao.maps.LatLngBounds()
            );

                this.map.setBounds(bounds);
            }
        },

    }
};
</script>

<style>
.map {
    width: 100%;
    height: 450px;
}
</style>
