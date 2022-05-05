<form action="${action}" method="${method}">
<#list controls as key, value>
    <div class="form-group">
      <label>${value.label} : </label>
      <input name="${value.name}" type="${value.type}" class="${value.class}"/>
    </div>
</#list>
</form>
