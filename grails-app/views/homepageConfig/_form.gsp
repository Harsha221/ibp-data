<div class="form-group">
    <label class="col-md-2 control-label" for="hashTags">
        Hash Tag
    </label>
    <div class="col-md-8">
        <input type="text" name="hashTags" id="hashTags" value="${homepageConfigInstance?.hashTags}" data-role="tagsinput"/>
    </div>
</div>
<div class="form-group">
    <label class="col-md-2 control-label" for="imageFiles">
        Banner Images
    </label>
    <div class="col-md-8">
        <div class="input-field">
            <div class="input-images-1" style="padding-top: .5rem;"></div>
        </div>
    </div>
</div>

<div class="form-group form-actions">
    <div class="col-md-10 col-md-offset-2">
        <button type="submit" class="btn btn-effect-ripple btn-primary">${homepageConfigInstance?.id ? 'Update' : 'Create'}</button>
    </div>
</div>
<asset:javascript src="image-uploader.min.js"/>
<g:if test="${homepageConfigInstance?.homePageAds?.size() > 0}">
    <script>
        let preloaded = [];
        <g:each in="${homepageConfigInstance?.homePageAds}" var="ads">
            preloaded.push({id: '${ads?.id}', src: '${ads?.imageUrl}'})
        </g:each>
        console.log('preloaded > ', preloaded)
        $('.input-images-1').imageUploader({
            preloaded: preloaded,
            imagesInputName: 'imageFiles',
            preloadedInputName: 'old'
        });
    </script>
</g:if>
<g:else>
    <script>
        $('.input-images-1').imageUploader();
    </script>
</g:else>