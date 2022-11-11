<template>
<div>
    <br>
    <div class="input-group mb-3">
        <label class="input-group-text">장소 검색</label>
        <input class="form-control" type="text" v-model="searchValue" >
        <button @click.prevent="searchPlace">검색</button>
    </div>
    <div class="map-area">
        <div>
            <div v-for="place in places" :key="place.id" @click="showOnMap(place)">
                <h3>{{ place.place_name }}</h3>
                <h3>{{ place.address_name }}</h3>
            </div>
        </div>
        <KakaoMapVue v-show="state" ref="kmap" class="kmap" :options="mapOption" />
    </div>
</div>
</template>

<script>
import KakaoMapVue from '@/components/map/NewKakaoMap.vue';
import MarkerHandler from "@/store/kakaoMap/marker-handler"
import { mapActions, mapGetters } from 'vuex';
// import api from "@/store/kakaoMap/api"
export default {
    components: {
        KakaoMapVue
    },
    computed: {
        ...mapGetters(['searchPlacesList']),
        places() {
            return this.searchPlacesList
        }
    },
    data() {
        return {
            searchValue:"",
            mapOption: {
                center: {
                    lat: 35.2053212919427,
                    lng: 126.811552010485,
                },
                level: 3,
            },
            markers: null,
            mapFlag: false
        }
    },

    created() {
        this.$store.dispatch('searchPlacesList', [])
        this.$store.dispatch('selectedPlace', {})
    },

    mounted() {
        const vueKakaoMap = this.$refs.kmap;
        this.markers = new MarkerHandler(vueKakaoMap)

        this.markers.add(this.places, (place) => {
            return { lat: place.lat, lng: place.lng };
        })
    },
    methods: {
        ...mapActions(['searchPlacesList']),
        searchPlace() {
            const keyword = this.searchValue;
            if (keyword.length === 0) {
                return;
            }
            const ps = new window.kakao.maps.services.Places()
            ps.keywordSearch(keyword, (data) => {
                this.$store.dispatch('searchPlacesList',data)
                const vueKakaoMap = this.$refs.kmap;
                this.markers = new MarkerHandler(vueKakaoMap)
        
                this.markers.add(data, (place) => {
                    return { lat: place.lat, lng: place.lng };
                })
            })
        },
        showOnMap(place) {
            this.$store.dispatch('selectedPlace',place)
            this.mapOption.center = {
                lat: place.y,
                lng: place.x
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