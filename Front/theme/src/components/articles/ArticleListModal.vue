<template>
    <div class="modal fade" :id="`articleListModal${idx}`" tabindex="-1" aria-labelled:by="`articleListModal${idx}`Label" aria-hidden="true">
        <div class="modal-dialog modal-fullsize">
            <div class="modal-content modal-fullsize">
                <div class="modal-header">
                    <h1 v-if="article" class="modal-title fs-5" :id="`articleListModal${idx}Label`">{{ article.name }}</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <!-- <ArticleDetailVue :article="article" class="article-detail"/> -->
                    {{ article }}
                    <ArticleDetailVue :article="article"/>
                </div>
                <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <!-- <button type="button" class="btn btn-primary">Save changes</button> -->
            </div>
        </div>
    </div>
</div>
</template>

<script lang="ts">
import ArticleDetailVue from './ArticleDetail.vue';
// import { reactive } from "vue";
export default {
    components: {
        ArticleDetailVue,
    },
    props: {
        article:Object,
        // articleName: String,
        // boardList:Object,
        idx:Number
    },
    setup(props:any) {
        console.log(props.boardList);
        // const article = reactive({
        //     themeName:"코딩카페",
        //     profile:"",
        //     nickname:"joe",
        //     picture:""
        // });
        const newArticle = {

        }

        return { newArticle }
    }
}
</script>

<style>
.modal-dialog.modal-fullsize {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}
.modal-content.modal-fullsize {
  height: auto;
  min-height: 100%;
  border-radius: 0; 
}
.modal-body{
    padding: 0 !important;
}

</style>