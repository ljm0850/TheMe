<template>
<div>
    <br>
    <div class="input-group mb-3">
        <label class="input-group-text">장소</label>
        <input class="form-control" type="text" v-model="searchValue" >
        <button @click.prevent="searchPlace" class="btn btn-outline-secondary white-add-button">검색</button>
    </div>
    <div class="map-area" v-if="searchFlag">
        <div v-if="placesFlag">
            <div v-for="place in places" :key="place.id">
                <div @click="showOnMap(place)" class="place-custom">
                    <h3>{{ place.place_name }}</h3>
                    <div class="place-location">{{ place.address_name }}</div>
                </div>
                <br>
            </div>
        </div>
        <button v-if="!mapFlag" @click.prevent="displayMap()" class="btn btn-outline-secondary white-add-button">지도</button>
        <!-- <button v-if="mapFlag" @click.prevent="displayMap()" class="btn btn-outline-secondary white-add-button">지도</button> -->
        <KakaoMapVue v-show="mapFlag" ref="kmap" class="kmap" :options="mapOption" />
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
            mapFlag: false,
            searchFlag: false,
            placesFlag: true
        }
    },
    
    computed: {
        ...mapGetters(['searchPlacesList']),
        places() {
            return this.searchPlacesList
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
            this.onSearchFlag()
            this.placesFlag = true
        },
        showOnMap(place) {
            this.$store.dispatch('selectedPlace',place)
            this.mapOption.center = {
                lat: place.y,
                lng: place.x
            }
            // this.offSearchFlag()
            this.placesFlag = false
            this.searchValue = place.place_name 
        },
        displayMap() {
            this.mapFlag = !this.mapFlag
        },
        onSearchFlag() {
            this.searchFlag = true
        },
        offSearchFlag() {
            this.searchFlag = false
        },
    }

};
</script>

<style>
.kmap {
    width: 100%;
    height: 390px;
}

.place-custom {
    background-color: white;
    border-radius: 10px;
}
.place-location {
    font-size: 14px;
    /* color: ; */
}
</style>