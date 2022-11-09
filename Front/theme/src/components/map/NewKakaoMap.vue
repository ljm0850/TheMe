<template>
    <div>
        <div class="kmap" ref="map"></div>
    </div>
</template>

<script>
let kakao = window.kakao
export default {
    props: ["options"],
    data() {
        return {
            mapInstance: null,
        }
    },
    mounted() {
        kakao = kakao || window.kakao
        console.log("이건 null 나오면 안됨",this.$refs.map);
        let container = this.$refs.map
        // let options = {
        //     center: new kakao.maps.LatLng(33.450701, 126.570667),
        //     level: 6
        // };
        const { center, level } = this.options
        this.mapInstance = new kakao.maps.Map(container, {
            center: new kakao.maps.LatLng(center.lat, center.lng),
            level,
        }); // 지도 생성, 객체 리턴
        // console.log("맵 인스턴스",this.mapInstance)
    },
    watch: {
        "options.center"(cur) {
            console.log("새로운 센터", cur.lat, cur.lng)
            this.mapInstance.setCenter(new kakao.maps.LatLng(cur.lat,cur.lng));
        }
    }
};
</script>

<style>
.kmap {
    width: 100%;
    height: 390px;
}
</style>