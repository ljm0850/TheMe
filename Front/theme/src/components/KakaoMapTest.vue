<template>
  <div>
    <h3>kakao map demo</h3>
    <div>
        <div class="searchbos">
            <div><input type="text" @keyup.enter="searchPlace"></div>
        </div>
        <div class="map-area">
            <div class="harbors">
                <div class="harbor" v-for="hbr in harbors" :key="hbr.seq" @click="showOnMap(hbr)">
                    <h3>{{hbr.place}}</h3>
                </div>
            </div>
            <KakaoMapVue ref="kmap" class="kmap" :options="mapOption"/>
        </div>
    </div>
    <!-- <div class="kmap" ref="map"></div> -->
  </div>
</template>

<script>
import KakaoMapVue from '@/components/map/KakaoMap.vue';
import MarkerHandler from "@/store/kakaoMap/marker-handler"
import api from "@/store/kakaoMap/api"
export default {
    components: {
        KakaoMapVue
    },
    data() {
        return {
            mapOption: {
                center: {
                    lat: 33.450701,
                    lng: 126.570667,
                },
                level:3,
            },
            harbors:[],
            markers:null,
        }
    },
    mounted() {
        api.harbor.all(res => {
            console.log("목록", res.harbors)
            this.harbors = res.harbors
        })
        const vueKakaoMap = this.$refs.kmap;
        this.markers = new MarkerHandler(vueKakaoMap)

        this.markers.add(this.harbors, (harbor)=>{
            return {lat:harbor.lat, lng:harbor.lng};
        })
    },
    methods: {
        searchPlace(e) {
            const keyword = e.target.value.trim();
            if (keyword.length === 0) {
                return;
            }
            const ps = new window.kakao.maps.services.Places()
            ps.keywordSearch(keyword, (data, status, pgn) => {
                console.log(data)
                console.log(status)
                console.log(pgn)
            })
        },
        showOnMap(harbor) {
            // console.log(harbor)
            this.mapOption.center = {
                lat: harbor.lat,
                lng: harbor.lng
            }
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